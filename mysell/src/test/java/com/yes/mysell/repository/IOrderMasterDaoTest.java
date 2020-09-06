package com.yes.mysell.repository;

import com.yes.mysell.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class IOrderMasterDaoTest {
    @Autowired
    private IOrderMasterDao orderMasterDao;

    @Test
    public void findByBuyerOpenid() {

    }

    @Test
    public void findByOrderId() {
        OrderMaster byOrderId = orderMasterDao.findByOrderId("1234");
        System.out.println(byOrderId);
    }
}