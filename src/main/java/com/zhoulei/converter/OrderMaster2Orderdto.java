package com.zhoulei.converter;

import com.zhoulei.dataobject.OrderMaster;
import com.zhoulei.dto.Orderdto;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2Orderdto {

    public static Orderdto convert(OrderMaster orderMaster){

        Orderdto orderdto=new Orderdto();
        BeanUtils.copyProperties(orderMaster,orderdto);
        return  orderdto;
    }

    public static List<Orderdto> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e ->
                convert(e)).collect(Collectors.toList());
    }
}
