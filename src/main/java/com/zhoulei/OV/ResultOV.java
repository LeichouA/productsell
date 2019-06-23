package com.zhoulei.OV;

import javafx.beans.binding.ObjectExpression;
import lombok.Data;

import java.util.List;

@Data
public class ResultOV<T> {

    private Integer code;

    private String message;

    private Object data;
}
