package com.yes.mysell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key,String value){
        if(redisTemplate.opsForValue().setIfAbsent(key, value)){
            return true;//可以set的话返回true，说明已经被锁住了
        }
        String currrentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if(!StringUtils.isEmpty(currrentValue)
                &&Long.parseLong(currrentValue)<System.currentTimeMillis()){
//            获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue)&&oldValue.equals(currrentValue)){
                return true;
            }
        }
        return false;

    }

    public void unlock(String key,String value){
        try{
            String currrentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currrentValue)&&currrentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("[redis分布式锁] 解锁异常,{}",e);
        }

    }

}
