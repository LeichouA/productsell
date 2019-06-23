package com.zhoulei.utils;

import com.zhoulei.OV.ResultOV;



public class ResultOVUtils {

    public static ResultOV success(Object object){
        ResultOV resultOV=new ResultOV();
        resultOV.setData(object);
        resultOV.setCode(0);
        resultOV.setMessage("成功");
        return resultOV;
    }

    public  static ResultOV sucess(){
        return success(null);
    }

    public static ResultOV error(Integer code,String msg) {
        ResultOV resultOV = new ResultOV();
        resultOV.setCode(1);
        resultOV.setMessage("失败");
        return resultOV;
    }

}
