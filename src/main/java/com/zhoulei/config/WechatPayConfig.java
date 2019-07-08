package com.zhoulei.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.exception.BestPayException;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.service.impl.WxPaySandboxKey;
import com.lly835.bestpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.ws.Action;

@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccoutConfig accoutConfig;

    @Bean
    public BestPayServiceImpl bestPayService(){

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config(){

        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(accoutConfig.getMpAppId());
        wxPayH5Config.setMchId(accoutConfig.getMchId());
        wxPayH5Config.setAppSecret(accoutConfig.getMpAppSecret());
        wxPayH5Config.setMchKey(accoutConfig.getMchKey());
        wxPayH5Config.setKeyPath(accoutConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(accoutConfig.getNotifyUrl());

        return wxPayH5Config;

    }
}
