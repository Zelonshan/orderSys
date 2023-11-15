package com.ntlg.ordersys.controller;

import com.ntlg.ordersys.pojo.Admin;
import com.ntlg.ordersys.result.Result;
import com.ntlg.ordersys.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AdminService adminService;
    //登录
    @PostMapping(value = "/login")
    public String login(Admin admin){
        String result = adminService.login(admin);
        if(result == "index"){
            return "index";
        }else {
        return "redirect:/login";
        }
    }

    //注册
    @PostMapping(value = "/regist")
    public String regist(Admin admin){
        if(adminService.regist(admin)){
            return "login";
        }
        return "redirect:/regist";
    }
}
