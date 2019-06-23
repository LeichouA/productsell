package com.zhoulei.controller;

import com.zhoulei.OV.ProductInfoOV;
import com.zhoulei.OV.ProductOV;
import com.zhoulei.OV.ResultOV;
import com.zhoulei.dataobject.ProductCategory;
import com.zhoulei.dataobject.ProductInfo;
import com.zhoulei.service.ProductCategoryService;
import com.zhoulei.service.ProductInfoService;
import com.zhoulei.utils.ResultOVUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {


    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;

    private static Logger logger=  LoggerFactory.getLogger(BuyerProductController.class);

    @GetMapping("/list")
    public ResultOV list(){

        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        logger.info(productInfoList.toString());
        List<Integer> list = new ArrayList<>();
        for (ProductInfo e : productInfoList) {
                list.add(e.getCategoryType());
        }
        List<ProductCategory> productCategories=productCategoryService.findByCategoryType(list);
        logger.info(productCategories.toString());
        List<ProductOV> productOVList=new ArrayList<>();
        for (ProductCategory productCategory:productCategories){

            ProductOV productOV=new ProductOV();
            productOV.setCategoryName(productCategory.getCategoryName());
            productOV.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoOV> productInfoList1=new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){

                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoOV productInfoOV=new ProductInfoOV();
                    /*productInfoOV.setProductId(productInfo.getProductId());
                    productInfoOV.setProductDescription(productInfo.getProductDescription());
                    productInfoOV.setProductIcon(productInfo.getProductIcon());
                    productInfoOV.setProductName(productInfo.getProductName());
                    productInfoOV.setProductPrice(productInfo.getProductPrice());*/
                    BeanUtils.copyProperties(productInfo,productInfoOV);
                    productInfoList1.add(productInfoOV);
                }
            }
            productOV.setProductInfos(productInfoList1);
            logger.info(productOV.toString());
            productOVList.add(productOV);
        }
        return ResultOVUtils.success(productOVList);
    }
}
