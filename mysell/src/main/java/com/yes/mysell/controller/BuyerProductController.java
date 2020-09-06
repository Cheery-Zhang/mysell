package com.yes.mysell.controller;

import com.yes.mysell.VO.ProductInfoVO;
import com.yes.mysell.VO.ProductVO;
import com.yes.mysell.VO.ResultVO;
import com.yes.mysell.dataobject.ProductCategory;
import com.yes.mysell.dataobject.ProductInfo;
import com.yes.mysell.service.ICategoryService;
import com.yes.mysell.service.IProductInfoService;
import com.yes.mysell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 买家商品。
 * 返回的json格式  @RestController
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private IProductInfoService productInfoService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product" , key = "123")
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo> upProductInfoList = productInfoService.findUpAll();
        // 2.查询类目（一次性查询）
        List<Integer> categoryTypelist = new ArrayList<>();
        for (ProductInfo productInfo:upProductInfoList
             ) {
            categoryTypelist.add(productInfo.getCategoryType());
        }
        //精简方法（java8 lambda）
//        list= upProductInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productcategoryList = categoryService.findByCategoryTypeIn(categoryTypelist);
        // 3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productcategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:upProductInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);

        }

       return ResultVOUtil.success(productVOList);
    }

}
