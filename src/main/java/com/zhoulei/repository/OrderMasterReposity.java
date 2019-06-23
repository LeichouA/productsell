package com.zhoulei.repository;
import com.zhoulei.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterReposity extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> buyerOpenid(String buyerOpenid, Pageable pageable);
}
