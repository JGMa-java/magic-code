package com.jgma.xcode.utils;

import java.util.Random;

/**
 * @Author: admin
 */
public class StringUtils {

    public static void main(String[] args) {
        boolean aNull = isBlank(" ");
        System.out.println(aNull);
    }

    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is
     * not empty and not null and not whitespace
     * @since 2.0
     */
    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * 随机截取 count 个字符
     *
     * @param source
     * @param count
     * @return
     */
    public static String randomCheckCode(String source, int count) {

        // 声明返回值
        String temp = "";

        // 验证码
        // 1-9，a-z A-Z ctrl+shift+X

        System.out.println("字符串的长度:" + source.length()); // 62

        Random random = new Random();

        for (int i = 0; i < count; i++) {

            // 随机获取 0-61 数字 4次 charAt(num);
            int num = random.nextInt(source.length());

            char c1 = source.charAt(num); // 索引从0开始 到61

            temp += c1;

        }

        return temp;

    }

}
