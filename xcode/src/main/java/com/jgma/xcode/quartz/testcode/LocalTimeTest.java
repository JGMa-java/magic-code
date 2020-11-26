package com.jgma.xcode.quartz.testcode;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

/**
 * @Author: admin
 */
public class LocalTimeTest {
    public static void main(String[] args) {
        // 获取当前时间
        LocalTime now = LocalTime.now();
// 设置时间
        LocalTime localTime = LocalTime.of(13, 51, 10);
//获取小时
        int hour = localTime.getHour();    // 结果：13
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY); // 结果：13
//获取分
        int minute = localTime.getMinute();  // 结果：51
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR); // 结果：51
//获取秒
        int second = localTime.getSecond();   // 结果：10
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE); // 结果：10

    }
}
