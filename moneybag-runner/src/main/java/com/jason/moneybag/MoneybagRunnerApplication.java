package com.jason.moneybag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jason.moneybag.**.mapper")
public class MoneybagRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneybagRunnerApplication.class, args);
    }

}
