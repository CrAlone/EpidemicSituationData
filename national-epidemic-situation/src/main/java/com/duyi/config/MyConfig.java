package com.duyi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * @author cr
 */
@Configuration
public class MyConfig {
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
