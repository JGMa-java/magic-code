package com.jgma.xcode.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: admin
 */
@Configuration
@ConfigurationProperties(prefix = "spring.encrypt")
public class EncryptProperties {
    // 16位字符串
    private final static String DEFAULT_KEY = "www.jgmajava.com";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
