package com.jgma.xcode.quartz.testcode;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * 1、Java8全新的日期和时间API
 * 2、LocalDate、LocalTime、LocalDateTime、Instant为不可变对象，修改这些对象对象会返回一个副本。增加、减少年数、月数、天数等 以LocalDateTime为例。
 * @Author: admin
 */
public class LocalDateTest {
    public static void main(String[] args) {
        // 获取当前日期
        LocalDate now = LocalDate.now();
// 设置日期
        LocalDate localDate = LocalDate.of(2019, 9, 10);
// 获取年
        int year = localDate.getYear();     //结果：2019
        int year1 = localDate.get(ChronoField.YEAR); //结果：2019
// 获取月
        Month month = localDate.getMonth();   // 结果：SEPTEMBER
        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR); //结果：9
// 获取日
        int day = localDate.getDayOfMonth();   //结果：10
        int day1 = localDate.get(ChronoField.DAY_OF_MONTH); // 结果：10
// 获取星期
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();   //结果：TUESDAY
        int dayOfWeek1 = localDate.get(ChronoField.DAY_OF_WEEK); //结果：2

    }
}
