package com.zhoulei.service.Impl;

import com.zhoulei.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryImplTest {

    @Autowired
    private ProductCategoryImpl productCategory;

    @Test
    public void findById() {
        ProductCategory s=productCategory.findById(1);
        System.out.println(s);
        Assert.assertEquals(new Integer(1),s.getCategoryId());
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByCategoryType() {
        List<Integer> list= Arrays.asList(2,3,4,10);
        List<ProductCategory> categoryType = productCategory.findByCategoryType(list);
        Assert.assertNotEquals(null,categoryType);
    }

    @Test
    public void save() {
    }
}