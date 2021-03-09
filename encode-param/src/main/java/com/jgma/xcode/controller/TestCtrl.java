package com.jgma.xcode.controller;

import com.jgma.xcode.common.BaseRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: admin
 */
@RestController
public class TestCtrl {

    @GetMapping("/index")
    public BaseRes index() {

        return BaseRes.ok();
    }
}
