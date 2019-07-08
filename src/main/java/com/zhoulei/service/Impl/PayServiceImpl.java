package com.zhoulei.service.Impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.zhoulei.dto.Orderdto;
import com.zhoulei.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    public static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public PayResponse create(Orderdto orderdto) {

        PayRequest payRequest = new PayRequest();
        payRequest.setOrderAmount(orderdto.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderdto.getOrderId());
        payRequest.setOpenid(orderdto.getBuyerOpenid());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】 request={}",payRequest);
        PayResponse payResponse =bestPayService.pay(payRequest);
        log.info("【微信支付】={}",payResponse);
        return payResponse;
    }

    @Override
    public String notify(String notifyData) {
        return null;
    }
}
