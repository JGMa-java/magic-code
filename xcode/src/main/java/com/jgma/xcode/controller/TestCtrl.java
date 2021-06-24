//package com.jgma.xcode.controller;
//
//import com.jgma.xcode.common.BaseRes;
//import com.jgma.xcode.common.annotation.Decrypt;
//import com.jgma.xcode.common.annotation.Encrypt;
//import com.jgma.xcode.pojo.User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Author: admin
// */
//@RestController
//public class TestCtrl {
//
//    @GetMapping("/getUser")
//    @Encrypt
//    public BaseRes index() {
//
//        User user = new User();
//        user.setId(99L);
//        user.setUsername("JUST DO IT.");
//        return BaseRes.ok("success",user);
//    }
//
//    @PostMapping("/user")
//    public BaseRes addUser(@RequestBody @Decrypt User user) {
//        return BaseRes.ok("ok", user);
//    }
//}
