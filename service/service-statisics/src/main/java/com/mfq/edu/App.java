package com.mfq.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/9 9:40
 * @description：
 * @modified By：
 * @version: $
 */
@SpringBootApplication
@ComponentScan("com.mfq")
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.mfq.edu.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


}
