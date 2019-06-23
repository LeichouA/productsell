package com.zhoulei.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),
    CART_EMPTY(2,"购物车为空"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_NOT_ENOUGH(11,"库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态错误"),
    ORDERMASTER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_NOTEXIST(16,"订单详情不存在"),
    ORDER_FINISHED_NOTEXIST(17,"订单完成不存在"),
    OREDER_PAYSTATUS_ERROR(18,"订单支付状态错误"),
    ORDER_OPENID_ERROR(19,"订单openid不匹配"),
    WTCHAT_MP_ERROR(20,"微信token出现异常")
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
