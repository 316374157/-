package com.phj.crowd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.entity.Admin;
import com.phj.crowd.utils.ResultEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.PrintWriter;

/**
 * @author 31637
 * @date 2020/8/4 13:02
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService crowdUserDetailsServiceImpl;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
//        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
//        //登录成功后的处理方法
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }


    public WebSecurityConfig(UserDetailsService crowdUserDetailsServiceImpl) {
        this.crowdUserDetailsServiceImpl = crowdUserDetailsServiceImpl;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.cors().and().csrf().disable();
        security
                .authorizeRequests()                 // 开启授权
                .antMatchers("/admin/logout","/admin/do/login")       //  对路径进行授权
                .permitAll()                        //  可以无条件访问
                .and()
                .authorizeRequests()                // 开启授权
                .anyRequest()                       // 对任意路径进行授权
                .authenticated()                    // 需要登录之后才能访问
                .and()
                .formLogin()                        // 开启登录验证
                .usernameParameter("loginAcct")
                .passwordParameter("userPswd")
                .loginProcessingUrl("/admin/do/login")
                // 登录成功的处理方法
                .successHandler((request,response,authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    ResultEntity success = ResultEntity.success(((SecurityAdmin) authentication.getPrincipal()).getOriginalAdmin());
                    out.write(new ObjectMapper().writeValueAsString(success));
                    out.flush();
                    out.close();
                })
                //登录失败的处理方法
                .failureHandler((request,response,authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString( ResultEntity.fail(CrowdConstant.MESSAGE_LOGIN_FAILED)));
                    out.flush();
                    out.close();
                })
                .and()
                .logout()
                .logoutUrl("/admin/logout")
                //登出成功的处理方法
                .logoutSuccessHandler((request,response,authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(ResultEntity.success(new Admin())));
                    out.flush();
                    out.close();
                })
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling()
                //权限拒绝处理逻辑
                .accessDeniedHandler((request,response,authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(ResultEntity.fail("权限不足，检查权限！")));
                    out.flush();
                    out.close();
                })
                //匿名用户访问无权限资源时的异常处理
                .authenticationEntryPoint((request,response,authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(ResultEntity.fail("权限不足，请先登录！")));
                    out.flush();
                    out.close();
                })
                //.and()
                //添加自定义的账号密码验证的filter
                //.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(crowdUserDetailsServiceImpl)
                .passwordEncoder(getBCryptPasswordEncoder());
    }
}