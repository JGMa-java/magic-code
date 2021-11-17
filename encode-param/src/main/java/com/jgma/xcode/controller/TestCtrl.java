package com.jgma.xcode.controller;

import com.jgma.xcode.common.BaseRes;
import com.jgma.xcode.common.annotation.Decrypt;
import com.jgma.xcode.common.annotation.Encrypt;
import com.jgma.xcode.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: admin
 */
@RestController
public class TestCtrl {

    private Logger logger = LoggerFactory.getLogger(TestCtrl.class);

    @GetMapping("/getUser")
    public BaseRes index() {
        User user = new User();
        user.setId(99L);
        user.setUsername("jgma");
        return BaseRes.ok("ok", user);
    }

    @GetMapping("/getEncryptUser")
    @Encrypt
    public BaseRes getUser() {
        User user = new User();
        user.setId(99L);
        user.setUsername("jgma");
        return BaseRes.ok("ok", user);
    }

    @PostMapping("/showDecryptUser")
    public BaseRes addUser(@RequestBody @Decrypt User user) {
        logger.info(user.toString());
        return BaseRes.ok("ok", user);
    }
}
