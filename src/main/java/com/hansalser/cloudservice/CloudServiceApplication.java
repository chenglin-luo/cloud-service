package com.hansalser.cloudservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hansalser.cloudservice.*.dao")
public class CloudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceApplication.class, args);
    }

}
