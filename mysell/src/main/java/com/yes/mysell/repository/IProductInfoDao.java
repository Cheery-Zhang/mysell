package com.yes.mysell.repository;

import com.yes.mysell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductInfoDao extends JpaRepository<ProductInfo,String> {
    /**根据商品的上架状态查询商品**/
    List<ProductInfo> findByProductStatus(Integer productStatus);

    ProductInfo findByProductId(String productId);



}
