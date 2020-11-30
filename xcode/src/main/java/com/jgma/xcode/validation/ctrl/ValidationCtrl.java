package com.jgma.xcode.validation.ctrl;

import com.jgma.xcode.validation.pojo.Person;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: admin
 */
@RestController
@RequestMapping("validation")
public class ValidationCtrl {

    @PostMapping("person")
    public String index(@RequestBody @Validated Person person){
        return "pass";
    }

}
