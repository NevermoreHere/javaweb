package com.example.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String uri = request.getRequestURI();

        //判断当前请求地址是否登录地址
        if(uri.contains("Login") || uri.contains("login") || uri.contains("register") || uri.contains("swagger"))
        {
            //登录请求，直接放行
            return true;
        }
        else
        {
            //判断用户是否登录
            //说明已经登录，放行
            HttpSession session = request.getSession();
            System.out.println(session.getAttribute(session.getId()));
            return session.getAttribute(session.getId()) != null;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) throws Exception {

    }
}
