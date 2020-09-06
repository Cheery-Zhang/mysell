package com.yes.mysell.aspect;

import com.yes.mysell.Constant.CookieConstant;
import com.yes.mysell.Constant.RedisConstant;
import com.yes.mysell.exception.SellerAuthorizeException;
import com.yes.mysell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *aop实现身份验证
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Pointcut("execution(public * com.yes.mysell.controller.Seller*.*(..))"
            +"&& !execution(public * com.yes.mysell.controller.SellerUserController.*(..))")//拦截哪些操作
    public void verify(){}

    @Before(value = "verify()")
    public void doVerify(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie==null){
            log.warn("[登入校验] cookie中查不到token");
            throw new SellerAuthorizeException();
        }
        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            log.warn("[登录校验] redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
