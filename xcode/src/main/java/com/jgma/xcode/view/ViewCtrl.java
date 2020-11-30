package com.jgma.xcode.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: admin
 */
@Controller
@RequestMapping("view")
public class ViewCtrl {

    @GetMapping("index")
    public String index(){
        return "templates";
    }

}
