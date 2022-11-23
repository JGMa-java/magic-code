package com.jgma.xcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@ServletComponentScan("com.jgma.xcode.filters")
@MapperScan("com.jgma.xcode.recursion.mapper")
public class XcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(XcodeApplication.class, args);
    }

}
