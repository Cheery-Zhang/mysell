package com.yes.mysell.service.impl;

import com.yes.mysell.dataobject.ProductCategory;
import com.yes.mysell.repository.IProductCategoryDao;
import com.yes.mysell.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private IProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findByCategoryId(Integer categoryId) {
        return productCategoryDao.findByCategoryId(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {
        return productCategoryDao.findByCategoryTypeIn(categoryType);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
