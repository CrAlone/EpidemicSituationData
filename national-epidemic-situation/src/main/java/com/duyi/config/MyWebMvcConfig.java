package com.duyi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**注册自定义拦截器
 * @author cr
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/*").excludePathPatterns("/authority","/FindBackPass","/find","/login","/registered","/update","/obtain","/*.css","/*.js");
    }
}
