package com.zhoulei.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@DynamicUpdate
@Data
@Proxy(lazy = false)
public class ProductInfo {

    @Id
    /*产品ID*/
    private String  productId;

    /*产品名称*/
    private String productName;

    /*产品单价*/
    private BigDecimal  productPrice;

    /*产品库存*/
    private  Integer productStock;

    /*产品描述*/
    private String productDescription;

    /*图片*/
    private String productIcon;

    /*商品状态*/
    private Integer productStatus;

    /*类目编号*/
    private Integer categoryType;

    public ProductInfo() {
    }
}
