/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: ParamObject.java
 * Author:   admin
 * Date:     2016-03-08 15:32:43
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-08 15:32:43><version><desc><source>
 *
 */

package com.seiko.base.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * API 通用参数类
 *
 */
public class ParamObject implements Serializable {

    private Integer pageNo;

    private Integer pageSize;

    private Integer totalNum;                // 总条数

    private Integer totelPageNum;            // 总页数

    private int     startRecorder;           // 从第一条开始

    private Map     paramMap = new HashMap();

    // private boolean filterEmpty=false;

    // public void setFilterEmpty(boolean filterEmpty) {
    // this.filterEmpty = filterEmpty;
    // }

    /**
     * 加入参数
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        // if(filterEmpty){
        if (value != null && !value.equals("")) {
            paramMap.put(key, value);
        }

        // }

    }

    public Integer getTotelPageNum() {
        if(pageSize!=null && totalNum!=null){
            totelPageNum = totalNum / pageSize;
            if (totalNum % pageSize > 0 || totalNum == 0) {
                totelPageNum += 1;
            }
        }else {
            totelPageNum = 0;
        }
        return totelPageNum;
    }

    public void setTotelPageNum(Integer totelPageNum) {
        this.totelPageNum = totelPageNum;
    }

    public Object getParam(String paramName) {
        if (paramMap.containsKey(paramName)) {

            return paramMap.get(paramName);

        }
        return null;
    }

    public String getStringParam(String paramName) {

        Object obj = getParam(paramName);
        if (obj != null && obj instanceof String) {

            return "" + obj;
        }
        return null;
    }

    public Integer getIntegerParam(String paramName) {

        Object obj = getParam(paramName);
        if (obj != null && obj instanceof Integer) {

            return (Integer)obj;
        }
        return null;
    }

    public Long getLongParam(String paramName) {

        Object obj = getParam(paramName);
        if (obj != null && obj instanceof Long) {

            return (Long) obj;
        }
        return null;
    }

    public Date getDateParam(String paramName) {

        Object obj = getParam(paramName);
        if (obj != null && obj instanceof Date) {

            return (Date) obj;
        }
        return null;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public int getStartRecorder() {
        if(pageNo != null && pageSize !=null ){
            return (pageNo - 1) * pageSize ;
        }else {
            return 0;
        }
    }

    public void setStartRecorder(int startRecorder) {
        this.startRecorder = startRecorder;
    }

    public Map getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map paramMap) {
        this.paramMap = paramMap;
    }

}
