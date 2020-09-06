package com.yes.mysell.repository;

import com.yes.mysell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class IProductInfoDaoTest {
    @Autowired
    private IProductInfoDao productInfoDao;

    @Test
    void findByProductStatus() {
    }

    @Test
    void findByProductId() {
        ProductInfo byProductId = productInfoDao.findByProductId("100");
        Assert.assertEquals(new String("100"), byProductId.getProductId());
    }
}