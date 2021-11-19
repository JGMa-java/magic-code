package com.jgma.xcode.controller;

import com.jgma.xcode.annotation.DataSource;
import com.jgma.xcode.config.DynamicDataSource;
import com.jgma.xcode.mapper.UserMapper;
import com.jgma.xcode.pojo.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TUserCtrl {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{name}/list")
    public List<TUser> list(@PathVariable("name")String name){

        DynamicDataSource.setDataSource(name);

        List<TUser> tUsers = userMapper.queryAllWithMaster();

        DynamicDataSource.clearDataSource();
        return tUsers;
    }

}
