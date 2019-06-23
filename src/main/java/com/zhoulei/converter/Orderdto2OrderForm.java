package com.zhoulei.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhoulei.Exception.SellException;
import com.zhoulei.dataobject.OrderDetail;
import com.zhoulei.dto.Orderdto;
import com.zhoulei.enums.ResultEnum;
import com.zhoulei.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Orderdto2OrderForm {

    public static Orderdto convert(OrderForm orderForm){

        Gson gson =new Gson();


        Orderdto orderdto =new Orderdto();
        orderdto.setBuyerName(orderForm.getName());
        orderdto.setBuyerPhone(orderForm.getPhone());
        orderdto.setBuyerAddress(orderForm.getAddress());
        orderdto.setBuyerOpenid(orderForm.getOpenId());

        List<OrderDetail> orderDetailList= new ArrayList<>();

        try {
            orderDetailList=gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换错误】，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderdto.setOrderDetailList(orderDetailList);
        return orderdto;

    }
}
