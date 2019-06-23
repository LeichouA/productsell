package com.zhoulei.controller;

import com.zhoulei.Exception.SellException;
import com.zhoulei.dto.Orderdto;
import com.zhoulei.enums.ResultEnum;
import com.zhoulei.service.OrderdtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderdtoService orderdtoService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl){

        //查询订单
        Orderdto orderdto = orderdtoService.findByone(orderId);
        if (orderdto == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

    }
}
