package com.yes.mysell.repository;

import com.yes.mysell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderMasterDao extends JpaRepository<OrderMaster,String> {
    /**根据买家的微信号查询订单主表**/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

   OrderMaster findByOrderId(String orderId);

}
