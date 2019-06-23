package com.zhoulei.OV;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhoulei.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProductOV {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoOV> productInfos;

}
