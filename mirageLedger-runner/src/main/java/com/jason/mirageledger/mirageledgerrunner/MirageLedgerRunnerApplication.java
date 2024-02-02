package com.jason.mirageledger.mirageledgerrunner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="com.jason.mirageledger")
@MapperScan("com.jason.mirageledger.**.mapper")
public class MirageLedgerRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MirageLedgerRunnerApplication.class, args);
    }

}
