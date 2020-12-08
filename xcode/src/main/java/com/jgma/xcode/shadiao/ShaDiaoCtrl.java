package com.jgma.xcode.shadiao;

import com.jgma.xcode.utils.HttpClientResult;
import com.jgma.xcode.utils.HttpClientUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 沙雕玩具
 * @Author: admin
 */
@RestController
@RequestMapping("shadiao")
public class ShaDiaoCtrl {

    /**
     * 彩虹屁
     */
    private String apiAppUrl = "https://chp.shadiao.app/api.php";

    @GetMapping("api.php")
    public String apiApp(){
        HttpClientResult httpClientResult = new HttpClientResult();
        try {
            httpClientResult = HttpClientUtils.doGet(apiAppUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpClientResult.getContent();
    }

}
