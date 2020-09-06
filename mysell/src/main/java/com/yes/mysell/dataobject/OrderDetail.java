package com.yes.mysell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    @Id
    private String detailId;//
    private String orderId;//订单id
    private String productId ;//商品id
    private String productName ; // 商品名称,
    private BigDecimal productPrice; //'单价',
    private Integer productQuantity ; //'商品数量',
    private String productIcon;//'商品小图',
   // private Date createTime; //'创建时间',
   // private Date updateTime; //'修改时间',
}
