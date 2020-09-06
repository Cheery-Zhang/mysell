package com.yes.mysell.service.impl;

import com.yes.mysell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void findByCategoryId() {
        ProductCategory  productCategory = categoryService.findByCategoryId(2);
        Assert.assertEquals(new Integer(2), productCategory.getCategoryId());
    }

    @Test
    void findAll() {
    }

    @Test
    void findByCategoryTypeIn() {
    }

    @Test
    void save() {
    }
}