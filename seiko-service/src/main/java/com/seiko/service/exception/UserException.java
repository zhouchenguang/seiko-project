/*
 * Copyright (C),2016-2017. 华住酒店管理有限公司
 * FileName: UserException.java
 * Author:   lijing
 * Date:     2017-05-18 11:55:11
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述
 * <lijing>  <2017-05-18 11:55:11> <version>   <desc>
 *
 */

package com.seiko.service.exception;


import com.seiko.base.exception.BaseException;

/**
 * Created by AIJING on 2017/3/6.
 */
public class UserException extends BaseException {

    public UserException(String message) {
        super(message);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, String module, String methodName) {
        super(message, module, methodName);
    }

    public UserException(String message, String module, String methodName, String request) {
        super(message, module, methodName, request);
    }
}
