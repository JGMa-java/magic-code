package com.jgma.xcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XcodeApplicationTests {

    @Value("${xcode.testContent}")
    private String testContent;

    @Test
    void contextLoads() {
        System.out.println(testContent);
    }

}
