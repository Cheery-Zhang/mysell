package com.yes.mysell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**商品包含类目**/
@Data
public class ProductVO implements Serializable {//面向前端的数据，需要序列化


    private static final long serialVersionUID = -5822308832308327336L;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;


}
