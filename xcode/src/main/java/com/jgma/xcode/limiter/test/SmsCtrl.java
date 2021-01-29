package com.jgma.xcode.limiter.test;

import com.jgma.xcode.common.RedisConstant;
import com.jgma.xcode.limiter.annotation.RateLimiter;
import com.jgma.xcode.utils.BaseRes;
import com.jgma.xcode.utils.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: admin
 */
@RestController
@RequestMapping("/sms")
public class SmsCtrl {

    @PostMapping("/send")
    @RateLimiter(name = RedisConstant.SMS_LIMIT_NAME, max = 3, key = "#mobile", timeout = 20L, extra = "smsLimiter")
    public BaseRes sendSmsCode(@RequestParam long mobile) {

        String s = StringUtils.randomCheckCode(mobile + "", 4);

        return BaseRes.ok().put(s);
    }

}
