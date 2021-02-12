package com.competition.firemon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.competition.firemon.dao")
@EnableTransactionManagement
@SpringBootApplication
public class FiremonApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiremonApplication.class, args);
    }

}
