package com.zhoulei.controller;

import com.lly835.bestpay.model.PayResponse;
import com.zhoulei.Exception.SellException;
import com.zhoulei.dto.Orderdto;
import com.zhoulei.enums.ResultEnum;
import com.zhoulei.service.OrderdtoService;
import com.zhoulei.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderdtoService orderdtoService;

    @Autowired
    private PayService  payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl,
                       Map<String, Object> map){

        //查询订单
        Orderdto orderdto = orderdtoService.findByone(orderId);
        if (orderdto == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //发起支付
        PayResponse payResponse = payService.create(orderdto);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("pay/create",map);
    }

    @PostMapping("/notify")
    public void notify(@RequestParam String notifyData){

        payService.notify(notifyData);

    }
}
