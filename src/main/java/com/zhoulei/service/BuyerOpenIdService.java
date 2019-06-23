package com.zhoulei.service;

import com.zhoulei.dto.Orderdto;

public interface BuyerOpenIdService {

    Orderdto findDetail(String openId,String orderId);

}
