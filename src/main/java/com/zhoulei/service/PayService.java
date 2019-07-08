package com.zhoulei.service;

import com.lly835.bestpay.model.PayResponse;
import com.zhoulei.dto.Orderdto;

public interface PayService {

    PayResponse create(Orderdto orderdto);

    String notify(String notifyData);
}
