package com.yes.mysell.service;

import com.yes.mysell.dto.OrderDTO;

/**
 * 买家
 */
public interface IBuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderid);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderid);

}
