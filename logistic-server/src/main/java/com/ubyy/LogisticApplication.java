package com.ubyy;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.ubyy.mapper")
public class LogisticApplication {

    public static void main(String[] args){
        SpringApplication.run(LogisticApplication.class, args);
    }
}
