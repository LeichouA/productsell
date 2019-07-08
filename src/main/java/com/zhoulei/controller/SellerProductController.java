package com.zhoulei.controller;

import com.zhoulei.Exception.SellException;
import com.zhoulei.dataobject.ProductInfo;
import com.zhoulei.enums.ResultEnum;
import com.zhoulei.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RequestMapping("/seller/product")
@Slf4j
@Controller
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){

        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        System.out.println(productInfoPage.toString());
        if (productInfoPage==null){
            log.error("【产品信息为空】 productInfoPage={}",productInfoPage);
            throw new SellException(ResultEnum.PRODUCT_INFO_NULL);
        }
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size", size);
        return new ModelAndView("product/list",map);

    }
}
