package com.yes.mysell.service.impl;

import com.yes.mysell.dataobject.SellerInfo;
import com.yes.mysell.repository.ISellerInfoDao;
import com.yes.mysell.service.ISellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements ISellerInfoService {
    @Autowired
    private ISellerInfoDao sellerInfoDao;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
       return sellerInfoDao.findByOpenid(openid);
    }
}
