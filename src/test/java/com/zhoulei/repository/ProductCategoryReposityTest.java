package com.zhoulei.repository;


import com.zhoulei.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryReposityTest {

    @Autowired
    private ProductCategoryReposity reposity;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = reposity.getOne(1);
        System.out.println("productCategory = " + productCategory);
    }

    @Test
    public void savaTest(){
       /* ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        reposity.save(productCategory);*/
        ProductCategory productCategory = reposity.getOne(2);
        productCategory.setCategoryType(10);

        reposity.save(productCategory);
    }

    @Test
    public void findBycategoryIn(){
        List<Integer> list= Arrays.asList(2,3,4);

        List<ProductCategory> result=reposity.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }

}