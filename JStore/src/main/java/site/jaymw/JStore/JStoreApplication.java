package site.jaymw.JStore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("site.jaymw.JStore.mapper")
@EnableTransactionManagement //开启事务管理
public class JStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(JStoreApplication.class, args);
    }

}
