package com.concordia.springboot.config;

import com.concordia.springboot.component.LoginHandlerInterceptor;
import com.concordia.springboot.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Locale;

/**
 * @author Fred Zhang
 * @create 2020-03-18 11:32 AM
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //browser send atguigu request and response success web page
        registry.addViewController("/atguigu").setViewName("success");
    }


    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adaptor = new WebMvcConfigurerAdapter() {

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");

            }
            //register an interceptor
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //screen out the targeted resources
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login","static/**","/asserts/**","/webjars/**");
            }
        };
        return adaptor;
    }

    @Bean
    public LocaleResolver localeResolver(){

            return new MyLocaleResolver();
        }

}
