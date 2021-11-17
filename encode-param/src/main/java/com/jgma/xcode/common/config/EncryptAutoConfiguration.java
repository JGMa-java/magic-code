package com.jgma.xcode.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * resources 目录下定义 META-INF，然后再定义 spring.factories 文件，内容如下：
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.jgma.xcode.common.config.EncryptAutoConfiguration
 *
 * @Author: admin
 */
@Configuration
@ComponentScan("com.jgma.xcode")
public class EncryptAutoConfiguration {
}
