package com.zhoulei.service.Impl;

import com.zhoulei.Exception.SellException;
import com.zhoulei.dto.Orderdto;
import com.zhoulei.enums.ResultEnum;
import com.zhoulei.service.BuyerOpenIdService;
import com.zhoulei.service.OrderdtoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerOpenIdServiceImpl implements BuyerOpenIdService {

    @Autowired
    private OrderdtoService orderdtoService;
    @Override
    public Orderdto findDetail(String openId, String orderId) {

        Orderdto orderdto = orderdtoService.findByone(orderId);
        if (orderdto==null){
            return null;
        }
        if (!orderdto.getBuyerOpenid().equalsIgnoreCase(openId)){
            log.error("openId 不匹配");
            throw new SellException(ResultEnum.ORDER_OPENID_ERROR);
        }

        return orderdto;
    }

}
