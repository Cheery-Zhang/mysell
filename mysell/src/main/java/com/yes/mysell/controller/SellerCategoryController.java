package com.yes.mysell.controller;

import com.yes.mysell.dataobject.ProductCategory;
import com.yes.mysell.form.CategoryForm;
import com.yes.mysell.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private ICategoryService categoryService;
    @RequestMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
            Map<String,Object> map){
        if(categoryId!=null){
            ProductCategory productCategory = categoryService.findByCategoryId(categoryId);
            map.put("productCategory", productCategory);
        }
        return new ModelAndView("category/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult  bindingResult,
                              Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/mysell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        ProductCategory productCategory = new ProductCategory();
        try{
            if(categoryForm.getCategoryId()!=null){
                productCategory=categoryService.findByCategoryId(categoryForm.getCategoryId());
            }else {
                productCategory.setCreateTime(new Date());
                productCategory.setUpdateTime(new Date());
            }
            BeanUtils.copyProperties(categoryForm, productCategory);
            categoryService.save(productCategory);
        }catch (SecurityException e){
            map.put("msg", e.getMessage());
            map.put("url", "/mysell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/mysell/seller/category/list");
        return new ModelAndView("common/success", map);
    }

}
