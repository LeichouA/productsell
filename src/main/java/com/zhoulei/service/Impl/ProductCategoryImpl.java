package com.zhoulei.service.Impl;

import com.zhoulei.dataobject.ProductCategory;
import com.zhoulei.repository.ProductCategoryReposity;
import com.zhoulei.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductCategoryImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryReposity productCategoryReposity;

    @Override
    public ProductCategory findById(Integer id) {

        ProductCategory productCategory = productCategoryReposity.getOne(id);
        return productCategory;
    }

    @Override
    public List<ProductCategory> findAll() {

        List<ProductCategory> reposityAll = productCategoryReposity.findAll();
        return reposityAll;
    }


    public List<ProductCategory> findByCategoryType(List<Integer> CategoryTypeList) {

        return productCategoryReposity.findByCategoryTypeIn(CategoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {

        return productCategoryReposity.save(productCategory);
    }
}
