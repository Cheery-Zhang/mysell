package com.yes.mysell.service.impl;

import com.yes.mysell.dto.OrderDTO;
import com.yes.mysell.enums.ResultEnum;
import com.yes.mysell.exception.SellException;
import com.yes.mysell.service.IBuyerService;
import com.yes.mysell.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements IBuyerService {
    @Autowired
    private IOrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
        return checkOrderOwner(openid, orderid);

    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderid) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderid);
        if(orderDTO==null){
            log.error("[取消订单] 查不到该订单 orderId={}",orderid);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderid){
        OrderDTO orderDTO = orderService.findOne(orderid);
        if(orderDTO==null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equals(openid)){
            log.error("【查询订单】 非本人查询 openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
