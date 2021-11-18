package com.jgma.xcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: admin
 */
@RestController
public class TestCtrl {

    @GetMapping("/getUser")
    public String index() {

        return "ok";
    }
}
