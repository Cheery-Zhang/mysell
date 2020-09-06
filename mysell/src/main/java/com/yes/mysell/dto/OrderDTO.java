package com.yes.mysell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yes.mysell.dataobject.OrderDetail;
import com.yes.mysell.enums.OrderStatusEnum;
import com.yes.mysell.enums.PayStatusEnum;
import com.yes.mysell.utils.EnumUtil;
import com.yes.mysell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**数据传输对象，专门在各个层里面传输用的**/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private List<OrderDetail> orderDetailList;

    private String orderId;
    private String buyerName ;
    private String buyerPhone ;
    private String buyerAddress ;
    private String buyerOpenid ;
    private BigDecimal orderAmount ;
    private Integer orderStatus;
    private Integer payStatus;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;// '创建时间',
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;// '修改时间',

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
       return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){

        PayStatusEnum byCode = EnumUtil.getByCode(payStatus, PayStatusEnum.class);
        return byCode;
    }
}
