package com.zhoulei.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhoulei.dataobject.OrderDetail;
import com.zhoulei.enums.CodeEnum;
import com.zhoulei.enums.OrderStatusEnum;
import com.zhoulei.enums.PayStatusEnum;
import com.zhoulei.utils.EnumUtil;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
/*@JsonSerialize(include = JsonSerialize.inclusion.NON_NULL)*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Orderdto {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){

        OrderStatusEnum orderStatusEnum = EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
        return orderStatusEnum;
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        PayStatusEnum payStatusEnum = EnumUtil.getByCode(payStatus, PayStatusEnum.class);
        return payStatusEnum;
    }
    }

