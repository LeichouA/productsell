package com.zhoulei.controller;

import com.zhoulei.OV.ResultOV;
import com.zhoulei.dataobject.ProductInfo;
import com.zhoulei.service.ProductCategoryService;
import com.zhoulei.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyerProductControllerTest {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductInfoService productInfoService;

    private static Logger logger=  LoggerFactory.getLogger(BuyerProductControllerTest.class);

    @Test
    public void list() {
        ResultOV resultOV=new ResultOV();
        resultOV.setCode(0);
        resultOV.setMessage("成功");
        List<ProductInfo> productInfoList = productInfoService.findUpAll();




    }
}