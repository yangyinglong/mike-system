package cn.hdu.fragmentTax.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {


    /**
     * 字串转为日期
     *
     * @param dateStr
     * @return
     */
    public static Date getDateFromString(String dateStr) {
        return getDateFromString(dateStr, null);
    }

    /**
     * 字串转为日期
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date getDateFromString(String dateStr, String pattern) {
        if ((pattern == null) || ("".equals(pattern))) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    /**
     * 字串转为日期
     *
     * @param dateStr
     * @param pattern
     * @param locale
     * @return
     */
    public static Date getDateFromString(String dateStr, String pattern, Locale locale) {
        if ((pattern == null) || ("".equals(pattern))) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        Date date;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    /**
     * 日期转为字串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getStrFromDate(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        String s = df.format(date);
        return s;
    }


    /**
     * 日期转字串
     *
     * @param date
     * @return
     */
    public static String getLongStrFromDate(Date date) {
        return getStrFromDate(date, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 增加月份
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }


    /**
     * 指定日期加上一个值
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date optTime(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 返回两个日期时间差 ms
     *
     * @param first
     * @param second
     * @return
     */
    public static long distance(Date first, Date second) {
        return second.getTime() - first.getTime();
    }

    /**
     * 时间差s
     *
     * @param first
     * @param second
     * @return
     */
    public static int distanceSec(Date first, Date second) {
        Long sec = Long.valueOf((second.getTime() - first.getTime()) / 1000L);
        return sec.intValue();
    }

    /**
     * 返回时间差m
     *
     * @param first
     * @param second
     * @return
     */
    public static int distanceMin(Date first, Date second) {
        return distanceSec(first, second) / 60;
    }

    /**
     * 返回时间差h
     *
     * @param first
     * @param second
     * @return
     */
    public static int distanceHour(Date first, Date second) {
        return distanceMin(first, second) / 60;
    }

    /**
     * 返回时间差d
     *
     * @param first
     * @param second
     * @return
     */
    public static int distanceDay(Date first, Date second) {
        return distanceHour(first, second) / 24;
    }

    /**
     * 返回时间差d
     *
     * @param first
     * @param second
     * @return
     */
    public static int distanceDay(String first, Date second) {
        return distanceHour(getDateFromString(first), second) / 24;
    }


    /**
     * 返回当前系统时间戳
     *
     * @return
     */
    public static Date getSysDatetime() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获得当前时间的时间戳 字符串
     * @return
     */
    public static String getSysDatetimeString(){
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * 返回当前系统时间-中文格式
     *
     * @return
     */
    public static String getCurrentDatetime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());  // new Date()为获取当前系统时间
    }

    /**
     *
     */
    public static String getChinaDateTime(String UTCString) throws ParseException {
        if (UTCString.indexOf("Z") == -1) {
            return UTCString;
        }
        UTCString = UTCString.replace("Z", " UTC");
        SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = utcFormat.parse(UTCString);
        return defaultFormat.format(date);
    }

    public static void main(String[] args) throws ParseException {
        String data = getChinaDateTime("2018-12-04T16:00:00.000Z");
        System.out.println(data);
    }
}