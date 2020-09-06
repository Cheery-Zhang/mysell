package com.yes.mysell.service.impl;

import com.yes.mysell.dataobject.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findByProductId() {
        System.out.println(productInfoService);
    }

    @Test
    public void findUpAll() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
    }

    @Test
    public void increaseStock() {
    }

    @Test
    public void decreaseStock() {
    }

    @Test
    public void onSale() {
    }

    @Test
    public void offSale() {
    }
}