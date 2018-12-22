/**
 *
 */

package com.github.bus.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * 生产随机数
 *
 * @author zy
 * @version 1.0
 * <p>
 * 变更履历：
 * v1.0 2015-3-6 zy 初版
 */
public class CreateRandomCode {

    /**
     * 生成随机数
     *
     * @param digit 想生成随机数的位数
     * @return 随机数
     */
    public static String getRandomNumber(int digit) {
        String random = "";
        Random r = new Random();
        int number = 0;
        for (int i = 0; i < digit; i++) {
            number = Math.abs(r.nextInt()) % 10;
            random += number;
        }
        // 保证第一个数字不为零
        if ("0".equals(random.substring(0, 1))) {
            String[] code = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "9"};
            number = Math.abs(r.nextInt()) % 10;
            random = random.replaceFirst("0", code[number]);
        }
        return random;
    }

    /**
     * 随机生成编码
     *
     * @param count 生成编码的位数
     * @return
     */
    public static String getRandomCode(int count) {
        // 返回编码
        String randomCode = "";
        // 生成随机类
        Random random = new Random();
        // 取随机产生的认证码
        String[] code = {"a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "m", "n", "p", "q", "r", "s",
                "t", "u", "v", "w", "x", "y", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",};
        // 数组长度
        int length = code.length;
        for (int i = 0; i < count; i++) {
            int start = random.nextInt(length);
            String rand = code[start];
            randomCode += rand;
        }
        return randomCode;
    }

    /**
     * 日期时间戳随机数（yyyyMMdd+指定位数随机数）
     *
     * @param digit 指定位数随机数
     * @return
     * @author zy
     */
    public static String getRandomDate(int digit) {
        return JodaTimeTools.getCurrentDate(JodaTimeTools.FORMAT_7) + getRandomNumber(digit);
    }

    /**
     * 日期时间戳随机数（yyMMddHHmmss+指定位数随机数）
     *
     * @param digit 指定位数随机数
     * @return
     * @author zy
     */
    public static String getRandomDateTime(int digit) {
        return JodaTimeTools.getCurrentDate(JodaTimeTools.FORMAT_12) + getRandomNumber(digit);
    }

    /**
     * 日期时间戳随机数（yyyyMMddHHmmss+指定位数随机数）
     *
     * @param digit 指定位数随机数
     * @return
     * @author zy
     */
    public static String getRandomFullDateTime(int digit) {
        return JodaTimeTools.getCurrentDate(JodaTimeTools.FORMAT_13) + getRandomNumber(digit);
    }


    /**
     * 32位UUID
     *
     * @return 32位UUID
     * @author zy
     */
    public static String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
