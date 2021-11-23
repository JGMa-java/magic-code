package com.jgma.xcode.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.mydb-master")
    public DataSource mydbMasterDataSource(){

        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.mydb-slave")
    public DataSource mydbSlaveDataSource(){

        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource mydbMasterDataSource, DataSource mydbSlaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put("mydb-master", mydbMasterDataSource);
        targetDataSources.put("mydb-slave", mydbSlaveDataSource);
        return new DynamicDataSource(mydbMasterDataSource, targetDataSources);
    }
}