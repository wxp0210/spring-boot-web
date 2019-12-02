package com.bjsxt.Configurer;

import com.bjsxt.MyHandlerInterceptor.MyHandlerInterceptor;
import com.bjsxt.Resolver.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.LocaleResolver;

//表明为配置类
//使用WebMvcConfigurer可以实现扩展springMvc
@Configuration
public class MyConFigurer implements WebMvcConfigurer {
    //配置视图控制器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器访问go请求也可跳转到success页面
        registry.addViewController("/go").setViewName("success");
    }


    //所有的WebMvcConfigurer会一起起作用
    @Bean//将WebMvcConfigurer组件放到容器中
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //定义拦截器
                registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/main.html")
                        .excludePathPatterns("/index.html","/","/login");
            }
        };

        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver(){

        return new MyLocaleResolver();
    }
}
