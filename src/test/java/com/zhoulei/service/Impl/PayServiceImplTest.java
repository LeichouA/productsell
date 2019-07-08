package com.zhoulei.service.Impl;

import com.zhoulei.dto.Orderdto;
import com.zhoulei.service.OrderdtoService;
import com.zhoulei.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderdtoService orderdtoService;

    @Test
    public void create() {

        Orderdto orderdto = orderdtoService.findByone("1560333581196268038");
        payService.create(orderdto);
    }
}