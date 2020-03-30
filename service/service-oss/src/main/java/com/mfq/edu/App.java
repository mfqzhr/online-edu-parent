package com.mfq.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/29 14:58
 * @description：阿里云oss
 * @modified By：
 * @version: v1$
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.mfq")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
