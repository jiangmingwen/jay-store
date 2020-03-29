package site.jaymw.JStore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.jaymw.JStore.interceptor.CorsInterceptor;
import site.jaymw.JStore.interceptor.LoginInterceptor;

import javax.annotation.Resource;

/**
 * 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private CorsInterceptor corsInterceptor;


    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 跨域拦截器配置
         */
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");

        /**
         * 需要登录的接口拦截
         */
        registry.addInterceptor(loginInterceptor).addPathPatterns("/api/admin/*/**");
    }
}
