package com.concordia.springboot.component;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Fred Zhang
 * @create 2020-03-18 9:09 PM
 */

/**
 * login checkout, no success login no authority
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //before the targeted method executes
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //no registration, go to login page
            request.setAttribute("msg","No authority, please log in");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;

        }else{
            //logged,go forward
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
