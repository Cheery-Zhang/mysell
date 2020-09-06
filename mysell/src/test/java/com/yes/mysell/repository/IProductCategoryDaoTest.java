package com.yes.mysell.repository;

import com.yes.mysell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class  IProductCategoryDaoTest {

    @Autowired
    private IProductCategoryDao productCategoryDao;

//    @Test
//    public void test(){
//        List<ProductCategory> all = pcdao.findAll();
//        for (ProductCategory p :all
//                ) {
//            System.out.println(p);
//        }
//
//    }

    @Test
    public void save(){
//        ProductCategory productCategory = new ProductCategory();
//        productCategory.setCategoryName("香菇肉丝");
//        productCategory.setCategoryType(3);
//        productCategoryDao.save(productCategory);

    }

    @Test
    public void save1(){
        ProductCategory byCategoryId = productCategoryDao.findByCategoryId(3);
        System.out.println(byCategoryId);

    }
    @Test
    @Transactional
    public void save2(){
        ProductCategory category = new ProductCategory("女生最爱", 10);
        ProductCategory result = productCategoryDao.save(category);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void save3(){
        List<Integer> list = Arrays.asList(2,3,10);
        List<ProductCategory> lists = productCategoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, lists.size());
        for (ProductCategory p:lists
        ) {
            System.out.println(p);
        }



    }

}