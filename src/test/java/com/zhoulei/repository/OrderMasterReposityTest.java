package com.zhoulei.repository;

import com.zhoulei.dataobject.OrderMaster;
import com.zhoulei.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterReposityTest {

    @Autowired
    private OrderMasterReposity orderMasterReposity;

    @Test
    public void save(){
        for(int i=0;i<40;i++){
            String s = KeyUtil.uniqueKey();
            OrderMaster orderMaster=new OrderMaster();
            orderMaster.setBuyerAddress("深圳市南山区");
            orderMaster.setBuyerName("周磊");
            orderMaster.setBuyerOpenid("12345");
            orderMaster.setBuyerPhone("18617193127");
            orderMaster.setOrderId(s);
            orderMaster.setOrderAmount(BigDecimal.valueOf(79));
            orderMasterReposity.save(orderMaster);

        }

    }

    @Test
    public void findOrder(){

        PageRequest pageRequest=new PageRequest(2,1);

        Page<OrderMaster> page=orderMasterReposity.buyerOpenid("12345",pageRequest);

        System.out.println(page.getTotalElements());


    }


}