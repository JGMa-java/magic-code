package com.jgma.xcode.quartz.testcode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

/**
 * @Author: admin
 */
public class LocalDateTimeTest {
    public static void main(String[] args) {
        // 获取当前日期时间
        LocalDateTime localDateTime = LocalDateTime.now();
// 设置日期
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        LocalDateTime localDateTime4 = localTime.atDate(localDate);
// 获取LocalDate
        LocalDate localDate2 = localDateTime.toLocalDate();
// 获取LocalTime
        LocalTime localTime2 = localDateTime.toLocalTime();
    }
}
