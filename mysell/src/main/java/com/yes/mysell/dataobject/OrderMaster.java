package com.yes.mysell.dataobject;

import com.yes.mysell.enums.OrderStatusEnum;
import com.yes.mysell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@DynamicUpdate
public class OrderMaster implements Serializable {


    private static final long serialVersionUID = -8041172196547643771L;//快捷键ctrl+shift+y
    @Id
    private String orderId; //订单id
    private String buyerName ;//买家姓名
    private String buyerPhone ;//买家手机号
    private String buyerAddress ;
    private String buyerOpenid ;//卖家微信
    private BigDecimal orderAmount ;//订单总金额
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();//订单状态，默认是0新创建
    private Integer payStatus= PayStatusEnum.WAIT.getCode();//支付状态，默认0未支付
    //private Date createTime;// '创建时间',
   // private Date updateTime;// '修改时间',

}
