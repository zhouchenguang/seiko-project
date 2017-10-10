package com.seiko.base.exception;

import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by AIJING on 2017/3/6.
 */
public class BaseException extends  RuntimeException {

    private String errCode;     //异常编码

    private String message;    //异常概述

    private String errKey;      //异常资源key名字编码  可以用模块+功能+关键词 例如 inv.stock.overinv

    private String module;      //异常模块 （订单模块、用户模块。。。）

    private String methodName;  //出现异常的方法

    private String errmsg;      //需要记录的异常信息

    private String request;     //异常的请求参数

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String  message,String module,String methodName) {
        super(message);
        this.message = message;
        this.module = module;
        this.methodName = methodName;
    }

    public BaseException(String  message,String module,String methodName,String request) {
        super(message);
        this.message = message;
        this.module = module;
        this.methodName = methodName;
        this.request = request;
    }


    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrKey() {
        return errKey;
    }

    public void setErrKey(String errKey) {
        this.errKey = errKey;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getErrmsg() {
        if(StringUtils.isBlank(errmsg)){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            this.printStackTrace(new PrintStream(baos));
            errmsg = baos.toString();
        }
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
