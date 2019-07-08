package com.zhoulei.service.Impl;

import com.zhoulei.Exception.SellException;
import com.zhoulei.dataobject.ProductInfo;
import com.zhoulei.dto.CartDTO;
import com.zhoulei.enums.ProductStatusEnum;
import com.zhoulei.enums.ResultEnum;
import com.zhoulei.repository.ProductInfoReposity;
import com.zhoulei.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductInfoImpl implements ProductInfoService {


    @Autowired
    private ProductInfoReposity productInfoReposity;

    @Override
    public ProductInfo findById(String id) {

        return productInfoReposity.getOne(id);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoReposity.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoReposity.findAll(pageable);
    }


    @Override
    public List<ProductInfo> findByProductStatus(Integer id) {
        return productInfoReposity.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoReposity.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo= productInfoReposity.getOne(cartDTO.getProductId());
            if (productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoReposity.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=productInfoReposity.getOne(cartDTO.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();

            if (result<0){
                throw  new SellException(ResultEnum.PRODUCT_NOT_ENOUGH);
            }

            productInfo.setProductStock(result);
            productInfoReposity.save(productInfo);
        }
    }

    @Override
    public void onSellProduct(String productId) {

        ProductInfo reposityOne = productInfoReposity.getOne(productId);
        if (reposityOne==null){
            throw new SellException(ResultEnum.PRODUCT_INFO_NULL);
        }
        if (reposityOne.getProductStatus()==ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }

        reposityOne.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfoReposity.save(reposityOne);
    }

    @Override
    public void offSellProduct(String productId) {

        ProductInfo productInfoReposityOne = productInfoReposity.getOne(productId);
        if (productInfoReposityOne==null){
            throw new SellException(ResultEnum.PRODUCT_INFO_NULL);
        }
        if (productInfoReposityOne.getProductStatus()==ProductStatusEnum.Down.getCode()){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }

        productInfoReposityOne.setProductStatus(ProductStatusEnum.Down.getCode());
        productInfoReposity.save(productInfoReposityOne);

    }
}
