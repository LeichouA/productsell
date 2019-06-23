package com.zhoulei.controller;

import com.zhoulei.Exception.SellException;
import com.zhoulei.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatControlller {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorsize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        //配置
        //调用方法
        String url = "http://m9nikm.natappfree.cc/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE,URLEncoder.encode(returnUrl));

        return "redirect:" + redirectUrl;

    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){



        try{
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            String openId = wxMpOAuth2AccessToken.getOpenId();
            return "redirect:" + returnUrl + "openId=" +openId;
        }catch (WxErrorException e){
            log.error("【微信授权】{}",e);
            throw new SellException(ResultEnum.WTCHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }




    }


}
