package com.zhoulei.service;

import com.zhoulei.dataobject.ProductInfo;
import com.zhoulei.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findById(String id);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findByProductStatus(Integer id);

    ProductInfo save(ProductInfo productInfo);

    /*加库存*/
    void increaseStock(List<CartDTO> cartDTOList);

    /*减库存*/
    void decreaseStock(List<CartDTO> cartDTOList);
}
