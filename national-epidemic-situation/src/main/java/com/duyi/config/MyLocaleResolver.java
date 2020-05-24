package com.duyi.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


/**国际化切换页面语言
 * @author cr
 */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //接受页面的参数
        String language = httpServletRequest.getParameter("language");
        //默认值
        Locale locale = Locale.getDefault();
        //判断传入过来的值是否为null
        if (!StringUtils.isEmpty(language)){
            //如果不为null则拆分信息
            String[] split = language.split("_");
            //返回拆分数据的结果
            return new Locale(split[0],split[1]);
        }
        return locale;
    }
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
