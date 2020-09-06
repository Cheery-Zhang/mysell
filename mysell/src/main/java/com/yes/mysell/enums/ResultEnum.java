package com.yes.mysell.enums;


public enum ResultEnum {
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    STOCK_NOT_ENOUGH(11,"库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态异常，不可取消"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"支付状态异常"),
    CART_EMPTY(18,"购物车为空"),
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),
    WX_MP_ERROR(20,"微信公众账号错误"),
    ORDER_CANCEL_SUCCESS(21,"订单取消成功"),
    ORDER_FINISH_SUCCESS(22,"订单完结成功"),
    PRODUCT_STATUS_ERROR(23,"商品状态异常"),
    LOGIN_FAIL(25,"登录失败"),
    LOGOUT_SUCCESS(26,"登出成功"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
