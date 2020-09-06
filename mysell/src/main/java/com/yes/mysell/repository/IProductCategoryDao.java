package com.yes.mysell.repository;


import com.yes.mysell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IProductCategoryDao extends JpaRepository<ProductCategory,Integer> {
    /**根据类目的编号**/
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
    /**根据类目的id找到相关的类目**/
    ProductCategory findByCategoryId(Integer categoryId);

}
