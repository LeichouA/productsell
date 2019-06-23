package com.zhoulei.service.Impl;

import com.zhoulei.dataobject.ProductInfo;
import com.zhoulei.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ProductInfoImplTest {

    @Autowired
    private ProductInfoService productInfo;
    @Test
    public void findById() {
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productInfo.findUpAll();
        System.out.println(upAll.size());
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest=new PageRequest(0,2);
        Page<ProductInfo> productInfoPage=productInfo.findAll(pageRequest);
        System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void findByProductStatus() {
    }

    @Test
    public void save() {
    }
}