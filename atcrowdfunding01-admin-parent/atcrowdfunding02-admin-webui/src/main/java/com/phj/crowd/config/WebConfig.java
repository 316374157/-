package com.phj.crowd.config;

import com.phj.crowd.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 * @author 31637
 * @date 2020/8/5 15:49
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    private final LoginHandlerInterceptor loginHandlerInterceptor;

    public WebConfig(LoginHandlerInterceptor loginHandlerInterceptor) {
        this.loginHandlerInterceptor = loginHandlerInterceptor;
    }

    /**
     * 注册自定义的拦截器
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns("/**")添加拦截路径（其他所有）
        //excludePathPatterns("/","/admin/do/login")排除拦截路径（首页和登录和登出）
//        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**").
//                excludePathPatterns("/","/admin/do/login","/admin/logout");
    }
}
