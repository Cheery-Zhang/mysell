package com.yes.mysell.controller;

import com.yes.mysell.dto.OrderDTO;
import com.yes.mysell.enums.ResultEnum;
import com.yes.mysell.exception.SellException;
import com.yes.mysell.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private IOrderService orderService;
    /**
     * 订单列表
     * page:第几页，从第一页开始
     * size：每页几条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "8") Integer size,
                             Map<String,Object> map
                             ){
        PageRequest request =PageRequest.of(page-1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list", map);

    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map) {
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);

        }catch (SellException e){
            log.error("[卖家端取消订单] 查询不到订单 {}",e);
            map.put("msg",e.getMessage());
            map.put("url","/mysell/seller/order/list");
            return  new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","/mysell/seller/order/list");
        return  new ModelAndView("common/success",map);


    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map) {
        OrderDTO orderDTO=new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);

        }catch (SellException e){
            log.error("[卖家端查询订单详情] 查询不到订单 {}",e);
            map.put("msg",e.getMessage());
            map.put("url","/mysell/seller/order/list");
            return  new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return  new ModelAndView("order/detail",map);

    }

    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,
                               Map<String,Object> map) {
        OrderDTO orderDTO =new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("[卖家端完结订单] 查询不到订单 {}",e);
            map.put("msg",e.getMessage());
            map.put("url","/mysell/seller/order/list");
            return  new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","/mysell/seller/order/list");
        return new ModelAndView("common/success", map);


    }
}
