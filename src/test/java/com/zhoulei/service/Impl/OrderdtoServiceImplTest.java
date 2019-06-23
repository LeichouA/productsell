package com.zhoulei.service.Impl;

import com.zhoulei.dataobject.OrderDetail;
import com.zhoulei.dataobject.OrderMaster;
import com.zhoulei.dto.Orderdto;
import com.zhoulei.enums.OrderStatusEnum;
import com.zhoulei.repository.OrderMasterReposity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderdtoServiceImplTest {

    private final String orderId="1233";

    @Autowired
    private OrderdtoServiceImpl orderdtoService;

    @Autowired
    private OrderMasterReposity orderMasterReposity;

    @Test
    public void create() {
        Orderdto orderdto = new Orderdto();
        orderdto.setBuyerAddress("深圳市福田");
        orderdto.setBuyerName("周磊");
        orderdto.setBuyerPhone("18617193127");
        orderdto.setBuyerOpenid("1101110");

        List<OrderDetail> orderDetailList= new ArrayList<>();
        OrderDetail orderDetail= new OrderDetail();
        orderDetail.setProductId("123");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);
        System.out.println(orderDetail.toString());

        orderdto.setOrderDetailList(orderDetailList);
        Orderdto orderdto1 = orderdtoService.create(orderdto);
        Logger logger= (Logger) LoggerFactory.getLogger(OrderdtoServiceImplTest.class);
        logger.info(orderdto1.toString());
        System.out.println(orderdto1.toString());

    }

    @Test
    public void findByone() {

        Orderdto orderdto = orderdtoService.findByone(orderId);
        System.out.println(orderdto.toString());

    }

    @Test
    public void orderlist() {
        PageRequest request= new PageRequest(0,2);
        Page<Orderdto> orderlist = orderdtoService.orderlist("12345", request);
        Assert.assertNotEquals(0,orderlist.getTotalElements());
        System.out.println(orderlist.toString());
    }

    @Test
    public void cancel() {
        Orderdto orderdto = orderdtoService.findByone(orderId);
        System.out.println(orderdto.getOrderDetailList().toString());
        Orderdto cancel = orderdtoService.cancel(orderdto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),cancel.getOrderStatus());
    }

    @Test
    public void finished() {
    }

    @Test
    public void paid() {
    }
}