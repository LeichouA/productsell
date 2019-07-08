package com.zhoulei.service.Impl;

import com.zhoulei.Exception.SellException;
import com.zhoulei.converter.OrderMaster2Orderdto;
import com.zhoulei.dataobject.OrderDetail;
import com.zhoulei.dataobject.OrderMaster;
import com.zhoulei.dataobject.ProductInfo;
import com.zhoulei.dto.CartDTO;
import com.zhoulei.dto.Orderdto;
import com.zhoulei.enums.OrderStatusEnum;
import com.zhoulei.enums.PayStatusEnum;
import com.zhoulei.enums.ResultEnum;
import com.zhoulei.repository.OrderDetailReposity;
import com.zhoulei.repository.OrderMasterReposity;
import com.zhoulei.service.OrderdtoService;
import com.zhoulei.service.ProductInfoService;
import com.zhoulei.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderdtoServiceImpl implements OrderdtoService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailReposity orderDetailReposity;

    @Autowired
    private OrderMasterReposity orderMasterReposity;

    BigDecimal orderAmout=new BigDecimal(BigInteger.ZERO);
    @Override
    @Transactional
    public Orderdto create(Orderdto orderdto) {

        String orderId=KeyUtil.uniqueKey();
        orderdto.setOrderId(orderId);
        //查询商品
        for (OrderDetail orderDetail:orderdto.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findById(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //计算价格 单价*数量
            orderAmout=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail
            .getProductQuantity())).add(orderAmout);

            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.uniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailReposity.save(orderDetail);
        }
        //写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderdto,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterReposity.save(orderMaster);

        //扣库存   javaSE 8 流库 与 lambda的应用
        List<CartDTO> cartDTOList=orderdto.getOrderDetailList().stream().map(
                e-> new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderdto;
    }

    @Override
    public Orderdto findByone(String orderId) {

        OrderMaster orderMaster=orderMasterReposity.getOne(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList= orderDetailReposity.findByOrderId(orderId);

        if(orderDetailList==null){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        Orderdto orderdto=new Orderdto();
        BeanUtils.copyProperties(orderMaster,orderdto);
        orderdto.setOrderDetailList(orderDetailList);

        return orderdto;
    }

    @Override
    public Page<Orderdto> orderlist(String buyerOpendid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterReposity.buyerOpenid(buyerOpendid, pageable);
        List<Orderdto> orderdtoList= OrderMaster2Orderdto.convert(orderMasterPage.getContent());
        Page<Orderdto> orderdtoPage=new PageImpl<>(orderdtoList,pageable,orderMasterPage.getTotalElements());

        return orderdtoPage;
    }

    @Override
    @Transactional
    public Orderdto cancel(Orderdto orderdto) {

        OrderMaster orderMaster = new OrderMaster();

        if(!orderdto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确，orderId={},orderStatus={}",orderdto.getOrderId(),orderdto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderdto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderdto,orderMaster);
        OrderMaster updateResult = orderMasterReposity.save(orderMaster);
        if(updateResult==null){
            log.error("订单更新失败，orderMaster={}",orderMaster);
            throw  new SellException(ResultEnum.ORDERMASTER_UPDATE_FAIL);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderdto.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情，ordeDTO={}",orderdto);
            throw new SellException(ResultEnum.ORDER_DETAIL_NOTEXIST);
        }
        List<CartDTO> cartDTOList=orderdto.getOrderDetailList().stream().map(
                e-> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //如果已支付需要退款
        if(orderdto.getPayStatus().equals(PayStatusEnum.SUCESS.getCode())){
            //TODO

        }
        return orderdto;
    }

    @Override
    @Transactional
    public Orderdto finished(Orderdto orderdto) {

        OrderMaster orderMaster = new OrderMaster();

        if(!orderdto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【完成订单】，订单状态不正确，orderId={},orderStatus",orderdto.getOrderId(),orderdto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderdto.setOrderStatus(OrderStatusEnum.Finished.getCode());
        BeanUtils.copyProperties(orderdto,orderMaster);
        OrderMaster save = orderMasterReposity.save(orderMaster);
        if (save==null){
            throw new SellException(ResultEnum.ORDER_FINISHED_NOTEXIST);
        }
        return orderdto;
    }

    @Override
    @Transactional
    public Orderdto paid(Orderdto orderdto) {
        OrderMaster orderMaster = new OrderMaster();

        if(!orderdto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【订单支付完成】，订单状态不正确，orderId={},orderStatus",orderdto.getOrderId(),orderdto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if(!orderdto.getPayStatus().equals(OrderStatusEnum.NEW)){
            log.error("【订单支付完成】，订单支付状态不正确，orderId={},orderStatus",orderdto.getOrderId(),orderdto.getPayStatus());
            throw new SellException(ResultEnum.OREDER_PAYSTATUS_ERROR);
        }
        orderdto.setOrderStatus(OrderStatusEnum.Finished.getCode());
        BeanUtils.copyProperties(orderdto,orderMaster);
        OrderMaster save = orderMasterReposity.save(orderMaster);

        if (save==null){
            throw new SellException(ResultEnum.OREDER_PAYSTATUS_ERROR);
        }
        return orderdto;
    }

    @Override
    public Page<Orderdto> findList(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterReposity.findAll(pageable);
        List<Orderdto> convert = OrderMaster2Orderdto.convert(orderMasterPage.getContent());
        return new PageImpl<>(convert,pageable,orderMasterPage.getTotalElements());
    }


}
