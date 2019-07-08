package com.zhoulei.controller;


import com.zhoulei.Exception.SellException;
import com.zhoulei.dto.Orderdto;
import com.zhoulei.enums.OrderStatusEnum;
import com.zhoulei.enums.ProductStatusEnum;
import com.zhoulei.enums.ResultEnum;
import com.zhoulei.service.OrderdtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderdtoService orderdtoService;

    @GetMapping("/list")
    public ModelAndView sellerOrder(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                    @RequestParam(value = "size",defaultValue = "10") Integer size,
                                    Map<String, Object> map){

        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<Orderdto> orderdtoPage = orderdtoService.findList(pageRequest);
        map.put("orderdtoPage",orderdtoPage);
        map.put("currentpage",page);
        map.put("size", size);
        return new ModelAndView("order/list",map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancellOrder(@RequestParam("orderId") String orderId,
                                     Map<String,Object> map){

        try {
            Orderdto orderdto = orderdtoService.findByone(orderId);
            orderdtoService.cancel(orderdto);
        }catch (SellException e){

            log.error("【订单为空】orderdto{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");

            return new ModelAndView("/common/error",map);
        }


            map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS);
            map.put("url","/sell/seller/order/list");

            return new ModelAndView("/common/success",map);
    }

    @GetMapping("/details")
    public ModelAndView orderDetails(@RequestParam("orderId") String orderId,
                                     Map<String,Object> map){
        Orderdto orderDTO = new Orderdto();
        try{
             orderDTO = orderdtoService.findByone(orderId);
        }catch (SellException e){
            log.error("【订单为空】orderdto={}",e);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");

            return new ModelAndView("/common/success",map);
        }

        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);

    }
}
