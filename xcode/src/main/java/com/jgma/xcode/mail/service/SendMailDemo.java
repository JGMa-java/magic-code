package com.jgma.xcode.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author: admin
 */
@RestController
@RequestMapping("mail")
public class SendMailDemo {
    private static JavaMailSenderImpl javaMailSender;
    Logger log = LoggerFactory.getLogger(SendMailDemo.class);

    static {
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");//链接服务器
        //javaMailSender.setPort(25);//默认使用25端口发送
        javaMailSender.setUsername("1520458127@qq.com");//账号
        javaMailSender.setPassword("授权码");//授权码
        javaMailSender.setDefaultEncoding("UTF-8");

        Properties properties = new Properties();
        //properties.setProperty("mail.debug", "true");//启用调试
        //properties.setProperty("mail.smtp.timeout", "1000");//设置链接超时
        //设置通过ssl协议使用465端口发送、使用默认端口（25）时下面三行不需要
        properties.setProperty("mail.smtp.auth", "true");//开启认证
        properties.setProperty("mail.smtp.socketFactory.port", "465");//设置ssl端口
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        javaMailSender.setJavaMailProperties(properties);
    }

    @RequestMapping("Send")
    public String mailSend(){
        log.info("--------------[mail/mailSend] start------------------");
        try {
            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom("1520458127@qq.com","JGMa-Xcode");
            helper.setTo("18638691837@163.com");
            helper.setSubject("测试邮件");
            helper.setText("测试邮件内容<h1 style=\"color: blueviolet;\">HelloWorld</h1>",true);
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("邮件发送失败", e.getMessage());
            e.printStackTrace();
        }
        log.info("--------------[mail/mailSend] end------------------");
        return "发送成功";
    }
}
