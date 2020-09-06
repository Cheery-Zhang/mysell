package com.zhang.ssm.service.impl;

import com.zhang.ssm.domain.Product;
import com.zhang.ssm.dao.IProductDao;
import com.zhang.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductService implements IProductService {
    @Autowired
    private IProductDao productDao;

    public List<Product> findAll() throws Exception {
        //return productDao.findAll();
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
