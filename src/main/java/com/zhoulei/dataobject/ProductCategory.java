package com.zhoulei.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DynamicUpdate
@Proxy(lazy = false)
@Data
public class ProductCategory<proxy> {

    /* 类目ID*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /*类目名字*/
    private String  categoryName;

   /*类目类型*/
    private Integer  categoryType;

    public ProductCategory() {
    }
}
