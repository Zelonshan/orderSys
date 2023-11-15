package com.ntlg.ordersys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("==========静态资源拦截！============");
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/imgs/**").addResourceLocations("file:C:/JAVA/javaworkspace/imgs/");
        registry.addResourceHandler("/wxadsimage/**").addResourceLocations("file:C:/JAVA/javaworkspace/imgs/wxadsimage/");
        registry.addResourceHandler("/foodlist/**").addResourceLocations("file:C:/JAVA/javaworkspace/imgs/foodlist/");
    }
    @Override
    public  void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/register").setViewName("register.html");
        registry.addViewController("/login").setViewName("login.html");
        //设置为最高优先级
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

    }
}