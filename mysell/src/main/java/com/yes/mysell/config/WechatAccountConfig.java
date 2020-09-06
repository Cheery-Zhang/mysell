package com.yes.mysell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    //公众平台id
    private String mpAppId;
    //公众平台密钥
    private String mpAppSecret;
    //
    private String mchId;
    private String mchKey;
    private String keyPath;
    private String notifyUrl;
    //开放平台
    private String openAppId;
    private String openAppSecret;
    /**
     * 微信模板id
     */
    private Map<String,String> templateId;
}
