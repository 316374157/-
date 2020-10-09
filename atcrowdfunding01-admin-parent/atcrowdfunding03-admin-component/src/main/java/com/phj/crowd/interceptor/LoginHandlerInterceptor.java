package com.phj.crowd.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 * @author 31637
 * @date 2020/8/5 15:25
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 登录验证
     * @param request 请求
     * @param response 响应
     * @param handler
     * @return true 表示通过验证
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session
        //HttpSession session = request.getSession();
        //获取要检查的信息
        //AdminVO admin = (AdminVO) session.getAttribute(CrowdConstant.ATTR_NAME_ADMIN_INFO);
        //进行判断,不通过抛出异常
        //if(admin == null){
        //    throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        //}
        //通过放行
        return true;
    }

}
