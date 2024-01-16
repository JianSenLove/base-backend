package com.jason.mirageledger.mirageledgerrunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value="com.jason.mirageledger")
public class MirageLedgerRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MirageLedgerRunnerApplication.class, args);
    }

}
