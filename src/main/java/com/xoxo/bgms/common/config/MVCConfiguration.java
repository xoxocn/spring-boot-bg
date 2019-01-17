package com.xoxo.bgms.common.config;

import com.xoxo.bgms.common.config.Interceptor.VerifySignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Package com.learn.ssmmodules.config
 * @Description mvc配置
 * @Author xiehua@zhongshuheyi.com
 * @Date 2018-11-09 19:43
 */
@Configuration
public class MVCConfiguration implements WebMvcConfigurer {
    @Bean
    public VerifySignInterceptor verifySignInterceptor() {
        return new VerifySignInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(verifySignInterceptor()).excludePathPatterns("/static/*")
                .excludePathPatterns("/error").addPathPatterns("/**");
    }

}

