/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: ReturnObject.java
 * Author:   admin
 * Date:     2016-03-08 15:32:43
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-08 15:32:43><version><desc><source>
 *
 */

package com.seiko.base.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * API 通用返回对象
 *
 */
public class ReturnObject<T> implements Serializable{

	private String returnCode;   //0 表示成功  1 2 3 。。。。。等表示各种失败   9  表示系统异常。

	private String returnKey;  //返回消息

	private String returnMsg;  //返回消息

    private T data;   //返回对象

    private Object object;   // 废弃

    private Object data2; //返回对象2

    private Object data3; //返回对象3


    private List<T> dataList; //返回列表对象；

    private Map<String,T> dataMap; //返回map对象

	private Map<Long,T> idVOMap; //主键->VO集合

    private Integer totalSize;

	private Integer totalPageSize;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}



	public String getReturnKey() {
		return returnKey;
	}

	public void setReturnKey(String returnKey) {
		this.returnKey = returnKey;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Map<String, T> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, T> dataMap) {
		this.dataMap = dataMap;
	}

	public Map<Long, T> getIdVOMap() {
		if(idVOMap==null){
			return new HashMap<>();
		}
		return idVOMap;
	}

	public void setIdVOMap(Map<Long, T> idVOMap) {
		this.idVOMap = idVOMap;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}



    public Object getData2() {
		return data2;
	}

	public void setData2(Object data2) {
		this.data2 = data2;
	}

	public Object getData3() {
		return data3;
	}

	public void setData3(Object data3) {
		this.data3 = data3;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(Integer totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	public static void main(String[] args){
    	ReturnObject<String> returnObject = new ReturnObject<String>();
    	returnObject.setData("aaaa");

    	String ret = returnObject.getData();
    	System.out.println(ret);

    }



}
