package com.zhoulei.enums;

import lombok.Getter;
import org.aspectj.apache.bcel.classfile.Code;

@Getter
public enum ProductStatusEnum implements CodeEnum {

    UP(0,"在架"),
    Down(1,"下架")
    ;

    private  Integer code;
    private  String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
