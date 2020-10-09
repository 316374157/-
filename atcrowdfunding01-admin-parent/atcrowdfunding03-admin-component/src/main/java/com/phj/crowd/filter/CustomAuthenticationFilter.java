package com.phj.crowd.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 从 axios 提交的数据中取出用户名和密码
 * @author 31637
 * @date 2020/8/19 9:48
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        ObjectMapper mapper = new ObjectMapper();
        UsernamePasswordAuthenticationToken authRequest = null;
        ServletInputStream is = null;
        Map<String,String> authenticationBean = null;
        try {
            is = request.getInputStream();
            authenticationBean = mapper.readValue(is, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        authRequest = new UsernamePasswordAuthenticationToken(
                    authenticationBean.get("loginAcct"), authenticationBean.get("userPswd"));
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}