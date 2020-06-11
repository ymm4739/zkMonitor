package com.example.zkmonitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.zkmonitor.mapper*")
public class ZkmonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkmonitorApplication.class, args);
    }

}
