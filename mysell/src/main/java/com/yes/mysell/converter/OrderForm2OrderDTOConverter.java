package com.yes.mysell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yes.mysell.dataobject.OrderDetail;
import com.yes.mysell.dto.OrderDTO;
import com.yes.mysell.enums.ResultEnum;
import com.yes.mysell.exception.SellException;
import com.yes.mysell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList= gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
            log.error("[对象转换] 错误 string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }



}