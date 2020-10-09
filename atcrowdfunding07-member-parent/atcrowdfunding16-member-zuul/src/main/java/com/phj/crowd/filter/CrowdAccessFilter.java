package com.phj.crowd.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.phj.crowd.constant.CrowdConstant;
import com.phj.crowd.utils.AccessPassResources;
import com.phj.crowd.utils.ResultEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author 31637
 * @date 2020/8/26 23:04
 */
public class CrowdAccessFilter extends ZuulFilter {
    @Override
    public boolean shouldFilter() {
        // 1.获取 RequestContext 对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 2.通过 RequestContext 对象获取当前请求对象（框架底层是借助 ThreadLocal 从当前 线程上获取事先绑定的 Request 对象）
        HttpServletRequest request = requestContext.getRequest();
        // 3.获取 servletPath 值
        String servletPath = request.getServletPath();
        // 4.根据 servletPath 判断当前请求是否对应可以直接放行的特定功能
        boolean containsResult = AccessPassResources.PASS_RES_SET.contains(servletPath);
        // 5.如果当前请求是可以直接放行的特定功能请求则返回 false 放行
        return !containsResult;
    }

    @Override
    public Object run() throws ZuulException {
        // 1.获取当前请求对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        // 2.获取当前 Session 对象
        HttpSession session = request.getSession();
        // 3.尝试从 Session 对象中获取已登录的用户
        Object loginMember = session.getAttribute(CrowdConstant.ATTR_NAME_MEMBER);
        // 4.判断 loginMember 是否为空
        if (loginMember == null) {
            // 5.从 requestContext 对象中获取 Response 对象
            HttpServletResponse response = requestContext.getResponse();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.write(new ObjectMapper().writeValueAsString(ResultEntity.fail(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.flush();
            out.close();
        }
        return null;
    }

    @Override
    public String filterType() {
        // 这里返回“pre”意思是在目标微服务前执行过滤
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
