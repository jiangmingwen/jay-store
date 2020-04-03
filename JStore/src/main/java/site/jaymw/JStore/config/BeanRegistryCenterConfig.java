package site.jaymw.JStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.jaymw.JStore.utils.MailUtils;

/**
 * Bean 注册中心
 */
@Configuration
public class BeanRegistryCenterConfig {

    /**
     * 邮箱工具类 bean 注册
     **/
    @Bean
    public MailUtils mailUtils() {
        return new MailUtils();
    }


}
