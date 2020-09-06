package com.yes.mysell.controller;

import com.yes.mysell.dataobject.ProductCategory;
import com.yes.mysell.dataobject.ProductInfo;
import com.yes.mysell.exception.SellException;
import com.yes.mysell.form.ProductForm;
import com.yes.mysell.service.ICategoryService;
import com.yes.mysell.service.IProductInfoService;
import com.yes.mysell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private IProductInfoService productInfoService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "8") Integer size,
                             Map<String,Object> map){
        PageRequest request =PageRequest.of(page-1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);//当前页
        map.put("size",size);
        return new ModelAndView("product/list", map);

    }
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        ProductInfo productInfo = new ProductInfo();
        try{
            productInfo = productInfoService.onSale(productId);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/mysell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("productInfo",productInfo);
        map.put("url","/mysell/seller/product/list");

        return new ModelAndView("common/success",map);
    }
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        ProductInfo productInfo = new ProductInfo();
        try{
            productInfo = productInfoService.offSale(productId);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("productInfo",productInfo);
        map.put("url","/sell/seller/product/list");

        return new ModelAndView("common/success",map);
    }

    /**
     * 修改
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                      Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.findByProductId(productId);
            map.put("productInfo",productInfo);

        }
        //查询所有的类目
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("product/index",map);

    }

    @PostMapping("/save")
//    @CachePut(cacheNames = "product",key ="123")
    @CacheEvict(cacheNames = "product",key ="123")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/mysell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo =new ProductInfo();
        try{
            //新增
            if(!StringUtils.isEmpty(form.getProductId())){
                productInfo = productInfoService.findByProductId(form.getProductId());
            }else {
                productInfo.setProductId(KeyUtil.getUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productInfoService.save(productInfo);
        }catch (SellException e){
            map.put("msg", e.getMessage());
            map.put("url", "/mysell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/mysell/seller/product/list");
        return new ModelAndView("common/success",map);

    }

}
