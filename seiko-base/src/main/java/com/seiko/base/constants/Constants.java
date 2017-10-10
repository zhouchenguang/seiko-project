package com.seiko.base.constants;


/**
 * Constant values used throughout the application.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public final class Constants {

    private Constants() {
        // hide me
    }

    /**
     * 公共状态
     */
    public static final String COMMON_STATUS_0 = "0";//初始化状态
    public static final String COMMON_STATUS_1 = "1";//成功
    public static final String COMMON_STATUS_2 = "2";//处理中
    public static final String COMMON_STATUS_3 = "3";//中间状态
    public static final String COMMON_STATUS_7 = "7";//禁用
    public static final String COMMON_STATUS_8 = "8";//失败
    public static final String COMMON_STATUS_9 = "9";//删除

    /**
     * 系统参数
     */
    public static String SYSTEM_ADMIN = "sysAdmin";

    /**
     * 系统配置sessionid
     */
    public static final String SESSION_SYSTEM_CONFIG = "seession_system_config";


    /**
     * YES/NO
     */
    public static String COMMMON_YES = "1";
    public static String COMMMON_NO = "0";

    /**
     * 返回code
     */
    public static String RETURN_CODE_SUCCESS = "0";                                           // 成功
    public static String RETURN_CODE_FAIL = "1";                                           // 失败
    public static String RETURN_CODE_ERROR = "9";                                           // 错误
    public static String RETURN_CODE_DATAERROR = "8";                                           // 错误
    public static String RETURN_CODE_NOTEXIST = "7";                                           // 数据不存在
    public static String RETURN_CODE_EXISTED = "12";                                          // 数据已存在

    public static String RETURN_CODE_PARAMFAIL = "-1"; //参数验证失败

    /**
     * 是否删除
     */
    public static final String DELETE_FLAG_FALSE = "0"; //未删除
    public static final String DELETE_FLAG_TRUE = "1"; //已删除


    /**
     * serialNum
     */
    public static final String SERIALNUM_NAME_ORDERNO = "orderNo"; //订单号


}

