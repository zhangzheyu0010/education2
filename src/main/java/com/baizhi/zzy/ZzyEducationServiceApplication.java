package com.baizhi.zzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baizhi.zzy.dao")
public class ZzyEducationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzyEducationServiceApplication.class, args);
    }

}
