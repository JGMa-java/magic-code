package com.jgma.xcode.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @Author: admin
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(ApplicationRunnerImpl.class);

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String path;

    private final String apiDocPath = "/japidoc/index.html";

    private final String readmePath = "/ReadMe.html";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();

        logger.info("系统启动成功 访问url:{}", "http://" + hostAddress + ":" + port + path + readmePath);
    }
}
