package com.yes.mysell.converter;

import com.yes.mysell.dataobject.OrderMaster;
import com.yes.mysell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTOConverter {
    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;

    }
    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){
       return orderMasterList.stream().map(e->
               converter(e)
       ).collect(Collectors.toList());
    }

}
