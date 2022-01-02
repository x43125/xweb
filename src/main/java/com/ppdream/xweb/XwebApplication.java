package com.ppdream.xweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(XwebApplication.class, args);
        System.out.println("http://localhost:8081");
    }

}
