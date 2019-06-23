package com.zhoulei.repository;

import com.zhoulei.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailReposityTest {

    @Autowired
    private OrderDetailReposity orderDetailReposity;

    @Test
    public void save(){


    }

    @Test
    public void findByOrderId(){

        List<OrderDetail> orderDetails = orderDetailReposity.findByOrderId("1233");
        System.out.println(orderDetails.size());
    }
}