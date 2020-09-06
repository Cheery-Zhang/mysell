package com.yes.mysell.service.impl;

import com.yes.mysell.converter.OrderMaster2OrderDTOConverter;
import com.yes.mysell.dataobject.OrderDetail;
import com.yes.mysell.dataobject.OrderMaster;
import com.yes.mysell.dataobject.ProductInfo;
import com.yes.mysell.dto.CartDTO;
import com.yes.mysell.dto.OrderDTO;
import com.yes.mysell.enums.OrderStatusEnum;
import com.yes.mysell.enums.PayStatusEnum;
import com.yes.mysell.enums.ResultEnum;
import com.yes.mysell.exception.ResponseBankException;
import com.yes.mysell.exception.SellException;
import com.yes.mysell.repository.IOrderDetailDao;
import com.yes.mysell.repository.IOrderMasterDao;
import com.yes.mysell.service.IOrderService;
import com.yes.mysell.service.IProductInfoService;
//import com.yes.mysell.service.IPushMessage;
import com.yes.mysell.service.WebSocket;
import com.yes.mysell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IProductInfoService productInfoService;
    @Autowired
    private IOrderDetailDao orderDetailDao;
    @Autowired
    private IOrderMasterDao orderMasterDao;
//    @Autowired
//    private IPushMessage pushMessage;
    @Autowired
    private WebSocket webSocket;


    @Override
    @Transactional
    public  OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);
//        List<CartDTO> cartDTOList = new ArrayList<>();
        //1.查询商品（数量，价格）
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo= productInfoService.findByProductId(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//                throw new ResponseBankException();
            }
            //2.计算总金额
            orderAmount=productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);

            orderDetailDao.save(orderDetail);
//
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }
        //3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);


        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e->
                new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());


        productInfoService.decreaseStock(cartDTOList);
        //发送websocket消息
        webSocket.sendMessage("有新的订单"+orderDTO.getOrderId());

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findByOrderId(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList=OrderMaster2OrderDTOConverter.converter(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster =new OrderMaster();
        //判断订单状态
       if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
           log.error("[取消订单] 订单状态异常 orderId = {},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
           throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
       }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster orderMasterUpdate = orderMasterDao.save(orderMaster);
        if(orderMasterUpdate==null){
            log.error("[取消订单] 更新失败， orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[取消订单] 订单中无商品详情 orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //如果已经支付，需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        OrderMaster orderMaster =new OrderMaster();
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[完结订单] 订单状态异常 orderId = {},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster orderMasterUpdate = orderMasterDao.save(orderMaster);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[完结订单] 更新失败 orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //推送模板消息
//        pushMessage.orderStatus(orderDTO);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {

        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[支付订单] 订单状态异常 orderId = {},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[支付订单] 订单支付状态异常 orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster =new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster orderMasterUpdate = orderMasterDao.save(orderMaster);
        if(orderMasterUpdate==null){
            log.error("[支付] 更新失败 orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterDao.findAll(pageable);
        List<OrderDTO> orderDTOList=OrderMaster2OrderDTOConverter.converter(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;

    }
}
