package com.zhoulei.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccoutConfig {

    private String mpAppId;

    private String mpAppSecret;

    private String mchId;

    private String mchKey;

    private String keyPath;

    private String notifyUrl;
}
