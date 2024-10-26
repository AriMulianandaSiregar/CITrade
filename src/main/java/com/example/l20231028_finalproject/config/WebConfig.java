package com.example.l20231028_finalproject.config;

import com.example.l20231028_finalproject.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录接口和注册接口不拦截
        registry.addInterceptor(loginInterceptor).
                excludePathPatterns(
                        "/",
                        "/tologin",
                        "/toregister",
                        "/admin/adminAdd",
                        "/admin/adminHome",
                        "/admin/adminAddC",
                        "/admin/adminManagement",
                        "/admin/addCatagory",
                        "/admin/logout",
                        "/user/login",
                        "/user/register",
                        "/user/logout",
                        "/item/addItem",
                        "/item/deleteItem",
                        "/item/editItem",
                        "/item/searchItem"
                );
    }


    @Value("${spring.servlet.multipart.location}")
    String path;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + path);
    }
}
