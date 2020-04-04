package com.mfq.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/4 0:56
 * @description：启动类
 * @modified By：
 * @version: v1$
 */
@SpringBootApplication
@MapperScan("com.mfq.edu.mapper")
@ComponentScan("com.mfq")
@EnableDiscoveryClient
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
