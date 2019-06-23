package com.zhoulei.Exception;

import com.zhoulei.OV.ResultOV;
import com.zhoulei.enums.ResultEnum;

public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){

        super(resultEnum.getMessage());
        this.code = code;
    }

    public SellException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code=code;
    }
}
