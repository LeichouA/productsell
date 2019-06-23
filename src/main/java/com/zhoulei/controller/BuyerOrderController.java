package com.zhoulei.controller;

import com.zhoulei.Exception.SellException;
import com.zhoulei.OV.ResultOV;
import com.zhoulei.converter.Orderdto2OrderForm;
import com.zhoulei.dto.Orderdto;
import com.zhoulei.enums.ResultEnum;
import com.zhoulei.form.OrderForm;
import com.zhoulei.service.BuyerOpenIdService;
import com.zhoulei.service.OrderdtoService;
import com.zhoulei.utils.ResultOVUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.nio.channels.SelectableChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderdtoService orderdtoService;

    @Autowired
    private BuyerOpenIdService buyerOpenIdService;

    //创建订单
    @PostMapping("/create")
    public ResultOV<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        Orderdto  orderdto= Orderdto2OrderForm.convert(orderForm);

        if(CollectionUtils.isEmpty(orderdto.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        Orderdto createResult = orderdtoService.create(orderdto);

        Map<String,String> map = new HashMap<>();

        map.put("orderId",createResult.getOrderId());
        return ResultOVUtils.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultOV<List<Orderdto>> listResultOV(@RequestParam("openId") String openId,
                                                 @RequestParam(value="page",defaultValue = "0") Integer page,
                                                 @RequestParam(value ="size",defaultValue = "10") Integer size){

        PageRequest pageRequest = new PageRequest(page,size);
        Page<Orderdto> orderlist = orderdtoService.orderlist(openId, pageRequest);

        return ResultOVUtils.success(orderlist.getContent());
    }
    //订单详情

    @GetMapping("/detail")
    public ResultOV<Orderdto> orderdtoResultOV(@RequestParam("openId") String openId,
                                               @RequestParam("orderId") String orderId){
        Orderdto orderdto = buyerOpenIdService.findDetail(openId,orderId);
        return ResultOVUtils.success(orderdto);
    }
    //取消订单

    @PostMapping("/cancel")
    public ResultOV orderdtoCancel(@RequestParam("openId") String openId,
                                   @RequestParam("orderId") String orderId){

        Orderdto orderdto = buyerOpenIdService.findDetail(openId,orderId);
        orderdtoService.cancel(orderdto);
        return ResultOVUtils.sucess();
    }

}
