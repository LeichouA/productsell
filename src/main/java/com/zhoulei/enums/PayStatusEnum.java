package com.zhoulei.enums;

import lombok.Getter;

@Getter
public enum  PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCESS(0,"支付成功")
    ;
    private Integer code;
    private String messeage;

    PayStatusEnum(Integer code, String messeage) {
        this.code = code;
        this.messeage = messeage;
    }
}
