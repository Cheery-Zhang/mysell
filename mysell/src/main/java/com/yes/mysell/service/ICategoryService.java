package com.yes.mysell.service;

import com.yes.mysell.dataobject.ProductCategory;

import java.util.List;


public interface ICategoryService {
    //后台管理用
    ProductCategory findByCategoryId(Integer categoryI);
    //后台管理用
    List<ProductCategory> findAll();
    //买家端用
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

    ProductCategory save(ProductCategory productCategory);
}
