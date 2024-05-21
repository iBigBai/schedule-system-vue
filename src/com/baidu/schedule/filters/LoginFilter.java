package com.baidu.schedule.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(urlPatterns = {"/showSchedule.html", "/schedule/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object sysUser = session.getAttribute("sysUser");
        if (null != sysUser) {
            // session中如果存在登录的用户 代表用户登录过,则放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //用户未登录,重定向到登录页
            response.sendRedirect("/login.html");
        }
    }
}
