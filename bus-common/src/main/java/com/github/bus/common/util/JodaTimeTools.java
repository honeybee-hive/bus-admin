
package com.github.bus.common.util;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * JODA-TIME日期工具类
 * 
 * @author zy
 * @version 1.0
 * 
 *          变更履历： v1.0 2014-10-26 zy 初版
 */
public class JodaTimeTools {

    public static final String FORMAT_1 = "yyyy/MM/dd hh:mm:ss.SSSa";

    public static final String FORMAT_2 = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_3 = "EEEE dd MMMM, yyyy HH:mm:ssa";

    public static final String FORMAT_4 = "yyyy/MM/dd HH:mm ZZZZ";

    public static final String FORMAT_5 = "yyyy/MM/dd HH:mm Z";

    public static final String FORMAT_6 = "yyyy-MM-dd";

    public static final String FORMAT_7 = "yyyyMMdd";

    public static final String FORMAT_8 = "ddHHmmsss";

    public static final String FORMAT_9 = "yyyyMMddHHmmssSSS";

    public static final String FORMAT_10 = "yyyy/MM/dd";

    public static final String FORMAT_11 = "yyyy-MM-dd HH:mm";

    public static final String FORMAT_12 = "yyMMddHHmmss";

    public static final String FORMAT_13 = "yyyyMMddHHmmss";

    /**
     * 取当前日期
     * 
     * @param format
     *            格式化
     * @return
     */
    public static String getCurrentDate(String format) {
        DateTime dateTime = new DateTime(new Date());
        return dateTime.toString(format);
    }

    /**
     * 
     * 当前日期时间
     * 
     * @author zy
     * @return
     */
    public static Date getDate() {
        DateTime dateTime = new DateTime(new Date());
        return dateTime.toDate();
    }

    /**
     * 字符日期转换日期类型
     * 
     * @param date
     *            支持格式 yyyy-MM-ddTHH:mm:ss,T划分时间
     * @return
     */
    public static Date toDate(String date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toDate();
    }

    /**
     * 字符串转日期格式
     * 
     * @author zcq
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date toDateTime(String date, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    /**
     * 日期格式化
     * 
     * @param date
     *            日期
     * @param format
     *            格式化
     * @return
     */
    public static String toString(Date date, String format) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }

    /**
     * 当前日期之前
     * 
     * @param n
     *            之前天数
     * @return
     */
    public static Date before_current_day(int n) {
        return before_day(new Date(), n);
    }

    /**
     * 日期之前（天）
     * 
     * @param date
     *            日期
     * @param n
     *            之前天数
     * @return
     */
    public static Date before_day(Date date, int n) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusDays(n).toDate();
    }

    /**
     * 日期之后（秒）
     * 
     * @author zy
     * @param date
     * @param n
     *            秒
     * @return
     */
    public static Date after_second(Date date, int n) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(n).toDate();
    }

    /**
     * 日期之后（天）
     * 
     * @author zcq
     * @param date
     * @param n
     * @return
     */
    public static Date after_day(Date date, int n) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(n).toDate();
    }

    /**
     * 日期之后（年）
     * 
     * @author zcq
     * @param date
     * @param n
     * @return
     */
    public static Date after_year(Date date, int n) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(n).toDate();
    }

    /**
     * 
     * 日期之前（年）
     * 
     * @author mnt_zhangjing
     * @param date
     * @param n
     * @return
     */
    public static Date before_year(Date date, int n) {
        DateTime dateTime = new DateTime(date);
        return dateTime.minusYears(n).toDate();
    }

    /**
     * 当前日期之前
     * 
     * @param n
     *            之前天数
     * @param format
     *            格式化
     * @return
     */
    public static String before_current_day_str(int n, String format) {
        return before_day_str(new Date(), n, format);
    }

    /**
     * 日期之前
     * 
     * @param date
     *            日期
     * @param n
     *            之前天数
     * @param format
     *            格式化
     * @return
     */
    public static String before_day_str(Date date, int n, String format) {
        return toString(before_day(date, n), format);
    }

    /**
     * 获取中文星期N
     * 
     * @param date
     * @return
     */
    public static String getWeekCN(Date date) {
        DateTime dateTime = new DateTime(date);
        // 星期
        switch (dateTime.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                return "星期日";
            case DateTimeConstants.MONDAY:
                return "星期一";
            case DateTimeConstants.TUESDAY:
                return "星期二";
            case DateTimeConstants.WEDNESDAY:
                return "星期三";
            case DateTimeConstants.THURSDAY:
                return "星期四";
            case DateTimeConstants.FRIDAY:
                return "星期五";
            case DateTimeConstants.SATURDAY:
                return "星期六";
        }
        return null;
    }

    /**
     * 获取日期差
     * 
     * @param begin
     * @param end
     * @return
     */
    public static int getDays(Date begin, Date end) {
        DateTime beginDtime = new DateTime(begin);
        DateTime endDtime = new DateTime(end);
        Period p = new Period(beginDtime, endDtime, PeriodType.days());
        return p.getDays();
    }

    /**
     * 获取年差
     * 
     * @param begin
     * @param end
     * @return
     */
    public static int getYears(Date begin, Date end) {
        DateTime beginDtime = new DateTime(begin);
        DateTime endDtime = new DateTime(end);
        Period p = new Period(beginDtime, endDtime, PeriodType.years());
        return p.getYears();
    }

    /**
     * 与系统当前日期比较
     * 
     * @param d1
     * @return 0相等,-1=d1<系统时间,1=d1>系统时间
     */
    public static int compareNow(Date d1) {
        DateTime dt1 = new DateTime(d1);
        if (dt1.isEqualNow()) {
            return 0;
        } else if (dt1.isBeforeNow()) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * 两日期比较
     * 
     * @param d1
     * @param d2
     * @return 0相等,-1=d1<d2,1=d1>d2
     */
    public static int compareDate(Date d1, Date d2) {
        DateTime dt1 = new DateTime(d1);
        DateTime dt2 = new DateTime(d2);
        if (dt1.isEqual(dt2)) {
            return 0;
        } else if (dt1.isBefore(dt2)) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * 获取n秒之后的calendar日历
     * 
     * <dl>
     * <dt>1 功能描述</dt>
     * <dd>获取calendar日历</dd>
     * </dl>
     * 
     * @author zcq
     * @param n
     *            秒
     * @return Calendar
     */
    public static Calendar getCalendarAfterSecond(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, n);
        return calendar;
    }

    /**
     * 
     * 获取出生日期
     * 
     * @author zy
     * @param birth
     *            格式：yyyy-MM-dd
     * @return
     */
    public static int getAgeFromBirth(String birth) {
        if (birth == null || "".equals(birth)) {
            throw new NullPointerException("请输入出生日期[yyyy-MM-dd]");
        }
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd").withZoneUTC(); // 时间解析
        LocalDate birthday = DateTime.parse(birth, format).toLocalDate();
        LocalDate now = new LocalDate();
        Years age = Years.yearsBetween(birthday, now);
        return age.getYears();
    }

    /**
     * 测试使用
     * 
     * @author dev001
     * @param args
     */
//    public static void main(String[] args) {
//
//        System.out.println(JodaTimeTools.getAgeFromBirth("1991-04-15"));
//
////        System.out.println(JodaTimeTools.toString(JodaTimeTools.getDate(), JodaTimeTools.FORMAT_2));
//    }

}
