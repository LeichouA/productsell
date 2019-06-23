package com.zhoulei.service;

import com.zhoulei.dto.Orderdto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderdtoService {

    Orderdto create(Orderdto orderdto);

    Orderdto findByone(String orderId);

    Page<Orderdto> orderlist(String buyerOpendid, Pageable pageable);

    Orderdto cancel(Orderdto orderdto);

    Orderdto finished(Orderdto orderdto);

    Orderdto paid(Orderdto orderdto);
}
