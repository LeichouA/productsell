package com.zhoulei.repository;

import com.zhoulei.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoReposityTest {

    @Autowired
    private ProductInfoReposity productInfoReposity;

    @Test
    public void findInfo(){
        ProductInfo productInfo =new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setProductName("披萨");
        productInfo.setProductPrice(BigDecimal.valueOf(79));
        productInfo.setProductStock(100);

        productInfoReposity.save(productInfo);
        
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> productInfoList = productInfoReposity.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}