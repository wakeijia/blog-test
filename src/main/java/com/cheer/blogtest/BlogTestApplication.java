package com.cheer.blogtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cheer.blogtest.dao")
@SpringBootApplication
public class BlogTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogTestApplication.class, args);
    }

}
