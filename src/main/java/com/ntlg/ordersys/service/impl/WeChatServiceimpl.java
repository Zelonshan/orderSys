package com.ntlg.ordersys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.ntlg.ordersys.service.WeChatService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatServiceimpl implements WeChatService {
    @Override
    public String codeToOpenId(String code) {
        String res;
        Map<String,String> data = new HashMap<String, String>();
        //小程序的appid
        data.put("appid", "#############");
        //小程序的appsecret
        data.put("secret", "#############");
        data.put("js_code", code);
        data.put("grant_type", "authorization_code");
        String response = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session").form(data).body();
        System.out.println("Response was: " + response);
        JSONObject obj= JSON.parseObject(response);//将json字符串转换为json对
        res= obj.get("openid").toString();
        return res;
    }
}
