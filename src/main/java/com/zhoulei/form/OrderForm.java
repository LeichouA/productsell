package com.zhoulei.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {

    /*
    * 买家xingm
    * */
    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "opendid必填")
    private String openId;

    @NotEmpty(message = "购物车不能为空")
    private String items;


}
