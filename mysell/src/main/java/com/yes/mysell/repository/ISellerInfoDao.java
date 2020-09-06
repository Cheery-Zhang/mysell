package com.yes.mysell.repository;

import com.yes.mysell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISellerInfoDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);
}
