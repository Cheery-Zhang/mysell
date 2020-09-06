package com.yes.mysell.handler;

import com.yes.mysell.VO.ResultVO;
import com.yes.mysell.config.ProjectUrlConfig;
import com.yes.mysell.exception.ResponseBankException;
import com.yes.mysell.exception.SellException;
import com.yes.mysell.exception.SellerAuthorizeException;
import com.yes.mysell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;
    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
       return new ModelAndView("redirect:"//跳转到登录页面
               .concat(projectUrlConfig.getWechatOpenAuthorize())
               .concat("/sell/wechat/qrAuthorize")
               .concat("?returnUrl=")
               .concat(projectUrlConfig.getSell())
               .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException(){
    }
}
