package com.zhoulei.service;

import com.zhoulei.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findById(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryType(List<Integer> CategoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
