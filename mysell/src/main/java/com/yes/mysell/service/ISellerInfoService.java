package com.yes.mysell.service;

import com.yes.mysell.dataobject.SellerInfo;

public interface ISellerInfoService {
    /**
     * 查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
