package com.yes.mysell.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductForm {

    private String productId;  //商品id
    private String productName; //商品名称
    private BigDecimal productPrice;  //商品价格
    private Integer productStock;//商品库存
    private  String productDescription; //商品描述
    private String productIcon;  //商品小图

    private Integer categoryType;  //类目编号，商品和类目的关系描述
}
