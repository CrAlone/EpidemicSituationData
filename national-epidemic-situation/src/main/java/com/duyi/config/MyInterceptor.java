package com.duyi.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**过滤器
 * @author cr
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    /**实现handlerInterceptor中的方法**/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过request获取session中的用户是否存在
        //若存在则放行
        if (request.getSession().getAttribute("user") != null){
            return true;
        }else {
            //若不存在转发到登录页面
            request.getRequestDispatcher("/login").forward(request,response);
        }
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
