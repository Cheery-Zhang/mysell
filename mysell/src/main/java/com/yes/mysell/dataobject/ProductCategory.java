package com.yes.mysell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name ="product_category")
@DynamicUpdate
@Data
public class ProductCategory implements Serializable {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY )
        private Integer categoryId;//类目id
        private String categoryName;
        private Integer categoryType;//类目编号
        private Date createTime;
        private Date updateTime;


        public ProductCategory() {
        }

        public ProductCategory(String categoryName, Integer categoryType) {
            this.categoryName = categoryName;
            this.categoryType = categoryType;
        }
}
