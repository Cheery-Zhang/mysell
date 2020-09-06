package com.yes.mysell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yes.mysell.enums.ProductStatusEnum;
import com.yes.mysell.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product_info")
@Data
@DynamicUpdate
public class ProductInfo implements Serializable {

    @Id
    private String productId;  //商品id
    private String productName; //商品名称
    private BigDecimal productPrice;  //商品价格
    private Integer productStock;//商品库存
    private  String productDescription; //商品描述
    private String productIcon;  //商品小图
    private Integer productStatus=ProductStatusEnum.UP.getCode(); //商品的状态，0正常1下架,
    private Integer categoryType;  //类目编号，商品和类目的关系描述
    private Date createTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
           return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
    }


}
