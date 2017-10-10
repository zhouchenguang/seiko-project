/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: DateUtil.java
 * Author:   admin
 * Date:     2016-03-08 15:33:15
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-08 15:33:15><version><desc><source>
 *
 */

package com.seiko.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by <a href="mailto:dan@getrolling.com">Dan
 * Kibler </a> to correct time pattern. Minutes should be mm not MM (MM is month).
 */
public final class DateUtil {
    private static Log log = LogFactory.getLog(DateUtil.class);
    public static final String TIME_PATTERN = "HH:mm";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_PATTERN = "yyyy/MM/dd";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private DateUtil() {
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
//        Locale locale = LocaleContextHolder.getLocale();
        String defaultDatePattern;
        try {
            defaultDatePattern = DATE_PATTERN;
//            defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale).getString("date.format");
        } catch (MissingResourceException mse) {
            defaultDatePattern = DEFAULT_DATE_PATTERN;
        }

        return defaultDatePattern;
    }

    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + DEFAULT_TIME_PATTERN;
    }

    /**
     * This method attempts to convert an Oracle-formatted date in the form dd-MMM-yyyy to mm/dd/yyyy.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date/time in the format you specify on input
     *
     * @param aMask   the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException when String doesn't match the expected format
     * @see SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate) throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format: MM/dd/yyyy HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.warn("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based on the System Property 'dateFormat' in the format
     * you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method generates a string representation of a date based on the System Property 'dateFormat' in the format
     * you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(String pattern, Date aDate) {
        return getDateTime(pattern, aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate) throws ParseException {
        return convertStringToDate(getDatePattern(), strDate);
    }

    /**
     * 取一天的开始
     *
     * @param aMask
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date getDaysBegin(String aMask, String strDate) {
        Date date = null;
        try {
            date = convertStringToDate(aMask, strDate);
            date = getDaysBegin(date);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return date;
    }

    public static Date getDaysBegin(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        return date;
    }

    /**
     * 取一天的结束
     *
     * @param aMask
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date getDaysEnd(String aMask, String strDate) {
        Date date = null;
        try {
            date = convertStringToDate(aMask, strDate);
            date = getDaysEnd(date);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return date;

    }

    public static Date getDaysEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        date = cal.getTime();
        return date;
    }

    public static Object getMonthBegin(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();
        return date;
    }

    public static Date getBeforeDate(Date date, Integer daysOftotalTransAmount) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -daysOftotalTransAmount);

        date = cal.getTime();
        return date;
    }

    public static Date addMonth(Date date, int monthamount) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);

        cal.add(Calendar.MONTH, monthamount);
        Date date2 = cal.getTime();
        return date2;
    }

    /**
     * 日期加减
     *
     * @param date 需要加减的日期
     * @param type 类型（年--Calendar.YEAR、月--Calendar.MONTH、日--Calendar.DATE）
     * @param num  加减数量（负数为减）
     * @return
     */
    public static Date addTime(Date date, int type, int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(type, num);
        return cal.getTime();
    }

    public static Integer[] getMonthsAndDaysBetween(Date early, Date late) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(early);
        c2.setTime(late);
        // 日
        int day1 = c1.get(Calendar.DATE);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);

        int day2 = c2.get(Calendar.DATE);

        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH);

        // c2.add(Calendar.DATE, 1);

        // System.out.println(getDateTime("yyyy-MM-dd",c1.getTime()));

        // System.out.println(getDateTime("yyyy-MM-dd",c2.getTime()));

        return days(year1, month1, day1, year2, month2, day2);
        // 月份

        // month = month > 0 ? month : Math.abs(month);

        /*
         * int years=year2-year1; int months = month2 - month1;
         * 
         * boolean flag=false;
         * 
         * 
         * int daysOfFeb=28; if(year1%4==0){ daysOfFeb=29; }
         * 
         * // c1.add(Calendar.YEAR, years);
         * 
         * int days = getDaysBetween(c1.getTime(),c2.getTime()); if(month1==0 && day1>=daysOfFeb ){ if(days>=daysOfFeb){
         * 
         * days=days-daysOfFeb; } months=months-1; }else{ days = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
         * if(days<0){ months=months-1; } }
         * 
         * months= years*12+months;
         * 
         * return new Integer[]{years,months,days};
         */

    }

    // 相差天数
    public static final int getDaysBetween(Date early, Date late) {

        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);

        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);

        // System.out.println(getDateTime("yyyy-MM-dd",calst.getTime()));

        // System.out.println(getDateTime("yyyy-MM-dd",caled.getTime()));
        // 得到两个日期相差的天数
        int days = (int) ((caled.getTime().getTime() - calst.getTime().getTime()) / 1000 / 3600 / 24);

        return days;
    }


    // 相差秒数
    public static final long getSecondssBetween(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        // 设置时间为0时
        // System.out.println(getDateTime("yyyy-MM-dd",calst.getTime()));

        // System.out.println(getDateTime("yyyy-MM-dd",caled.getTime()));
        // 得到两个日期相差的天数
        long seconds = (int) ((caled.getTime().getTime() - calst.getTime().getTime()) / 1000);
        return seconds;
    }

    /**
     * @param year1
     * @param month1
     * @param day1
     * @param year2
     * @param month2
     * @param day2
     * @return list[0] 天数，list[0] 是否需要月份减1
     */
    private static Integer[] days(int year1, int month1, int day1, int year2, int month2, int day2) {

        int daysOfMonth1 = getDaysOfMonth(year1, month1);

        int daysOfMonth2 = getDaysOfMonth(year2, month2);

        // 修正日期
        int addDays = daysOfMonth1 - daysOfMonth2;

        int days = 0;
        int subMonth = 0;
        if (month2 != 1 && day2 >= day1 - 1) {
            days = day2 - day1 + 1;
            subMonth = 0;

        } else if (month2 == 1) { // 二月
            if (month1 == 1) {
                days = day2 - day1 + 1;

            } else {

                days = daysOfMonth1 - day1 + 1 + day2;
                subMonth = 1;
            }

        } else {

            days = daysOfMonth1 - day1 + 1 + day2;
            subMonth = 1;

        }

        // kuan yue
        if (month2 != month1) {
            // days =days+addDays;

        }

        // 15 tian full month
        // if(day2>15){

        if (days >= daysOfMonth2 && day2 == daysOfMonth2) {
            subMonth = subMonth - 1;
            days = days - daysOfMonth2;

        }
        // if(day2==daysOfMonth2 && month2!=month1){
        // days =days + addDays;

        // }

        // }else{

        if (days >= daysOfMonth1) {
            subMonth = subMonth - 1;
            days = days - daysOfMonth1;
        }
        // }

        int subYear = 0;
        int months = 0;
        if (month2 - subMonth >= month1) {

            months = month2 - month1 - subMonth;
            subYear = 0;
        } else {

            months = 12 - month1 + month2 - subMonth;

            subYear = 1;
        }

        int years = year2 - year1 - subYear;

        /*
         * List list = new ArrayList(); list.add(days); list.add(subMonth);
         */

        return new Integer[]{years, months, days};

    }

    private static int getDaysOfMonth(int year, int month) {
        int days = 0;
        switch (month + 1) {
            case 2:
                int daysOfFeb = 28;
                if (year % 4 == 0) {
                    daysOfFeb = 29;
                }
                days = daysOfFeb;
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            default:
                days = 30;
        }
        return days;
    }

    public static Set getAllDateForDateRange(Date beginDate, Date endDate) {
        Set set = new HashSet();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        double between = (endDate.getTime() - beginDate.getTime()) / 1000;// 除以1000是为了转换成秒
        double day = between / (24 * 3600);
        for (int i = 0; i < day; i++) {
            Calendar cd = Calendar.getInstance();
            cd.setTime(beginDate);
            cd.add(Calendar.DATE, i);// 增加一天
            set.add(sdf.format(cd.getTime()));
        }
        return set;
    }

    public static long getDateline() {
        return System.currentTimeMillis() / 1000;
    }

    public static Long getDateline(String date) {
        try {
            return convertStringToDate("yyyy-MM-dd", date).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long getDateHaveHour(String date) {
        try {
            return convertStringToDate("yyyy-MM-dd HH", date).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long getDateline(String date, String pattern) {
        try {
            return convertStringToDate(pattern, date).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String aa = "";
//        List list = new ArrayList();
//        for (int i = 1; i < 40; i++) {
//            list.add(i);
//        }
//        System.out.println("===========" + list + "aaa");
        StringBuffer sb = new StringBuffer();
        sb.append("aaaaaaaa").append(" bcd ");
        sb.delete(sb.lastIndexOf("cd"), sb.length());
        System.out.print(sb.toString());
    }

    /**
     * 获取当前日期 星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}
