package com.seiko.utils;


import org.apache.commons.lang.StringUtils;

/**
 * Created by lovemelon on 2016/6/7.
 */
public class ParamsFilterUtil {

    /**
     * 将容易引起xss漏洞和sql注入的字符直接替换
     *
     * @param value
     * @return
     */
    public static String cleanXSS(String value) {
        if (StringUtils.isEmpty(value)) return null;
        // You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
                "\"\"");
        value = value.replaceAll("-xie-", "/").trim();
        value = value.replaceAll("%", "").trim();
        value = value.replaceAll("\'", "").trim();
        value = value.replaceAll("script", "");
        return value;
    }

    /**
     * 将容易引起xss漏洞和sql注入的字符直接替换
     *
     * @param value
     * @return
     */
    public static String cleanXSSEasy(String value) {
        if (StringUtils.isEmpty(value)) return null;
        // You'll need to remove the spaces from the html entities below
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("-xie-", "/").trim();
        value = value.replaceAll("%", "").trim();
        value = value.replaceAll("\'", "").trim();
        value = value.replaceAll("script", "");
        return value;
    }

}
