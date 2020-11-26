package com.jgma.xcode.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @Author: admin
 */
@RestController
@RequestMapping("sourceType")
public class SourceTypeTest {

    @Autowired
    private DataSource dataSource;

    @GetMapping("index")
    public void index(){
        System.out.println(dataSource);
    }

}
