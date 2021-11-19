package com.jgma.xcode;

import com.jgma.xcode.config.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan("com.jgma.xcode.common.filters")
@MapperScan(basePackages = "com.jgma.xcode.mapper")
@Import({DynamicDataSourceConfig.class})
public class EncodeParamApplication {

    public static void main(String[] args) {
        SpringApplication.run(EncodeParamApplication.class, args);
    }

}
