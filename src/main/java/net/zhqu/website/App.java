package net.zhqu.website;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"net.zhqu.framework.**", "net.zhqu.website.**"})
@MapperScan(value = {"net.zhqu.framework.dao.imp","net.zhqu.website.**.dao"})
@Configuration
@EnableTransactionManagement
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
