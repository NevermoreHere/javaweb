package com.example.Interceptor;

import com.alibaba.fastjson.JSON;
import com.example.User.entity.User;
import com.example.Utils.CommonResult;
import com.example.Utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String uri = request.getRequestURI();
        System.out.println(uri);
        //判断当前请求地址是否登录地址
        if(uri.contains("Login") || uri.contains("login") || uri.contains("register") || uri.contains("swagger") || request.getMethod().toUpperCase().equals("OPTIONS") || uri.contains("feign") || uri.contains("actuator"))
        {
            return true;
            //登录请求，直接放行
        }
        else
        {
            //判断用户是否登录
            //说明已经登录，放行
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute(session.getId());
            if (token == null){
                token = request.getHeader("token");
            }
            if (token == null){
                response.setStatus(401);

                return false;
            }
            User user = JwtUtils.verifyToken(token);
            if (user == null){
                response.setStatus(401);
                return false;
            }
            request.setAttribute("user", user);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) throws Exception {

    }
}
