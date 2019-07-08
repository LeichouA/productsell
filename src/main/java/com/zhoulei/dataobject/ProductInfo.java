package com.zhoulei.dataobject;

import com.zhoulei.enums.ProductStatusEnum;
import com.zhoulei.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

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

    private Date createTime;

    private  Date updateTime;

    public ProductInfo() {
    }

    public ProductStatusEnum getProductStatusEnum(){

        ProductStatusEnum productStatusEnum = EnumUtil.getByCode(productStatus, ProductStatusEnum.class);

        return productStatusEnum;
    }
}
