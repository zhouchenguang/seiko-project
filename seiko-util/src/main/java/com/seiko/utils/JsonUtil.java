/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: JsonUtil.java
 * Author:   admin
 * Date:     2016-03-08 15:33:15
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-08 15:33:15><version><desc><source>
 *
 */

package com.seiko.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import java.util.List;

public class JsonUtil {


    public static String JSON_Bean2String(Object ob, String[] filterFields) {
        JsonConfig j = new JsonConfig();
        j.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        j.setIgnoreDefaultExcludes(false); //设置默认忽略
        j.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
        j.setExcludes(filterFields);
        JSONObject jsonObj = JSONObject.fromObject(ob, j);
        return jsonObj.toString();
    }

    public static String JSON_Bean2String(Object ob) {
        JsonConfig j = new JsonConfig();
        j.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        j.setIgnoreDefaultExcludes(false); //设置默认忽略
        j.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
        JSONObject jsonObj = JSONObject.fromObject(ob, j);
        return jsonObj.toString();
    }

    public static String JSON_Bean2StringWithoutLazy(Object ob) {
        JsonConfig j = new JsonConfig();
        j.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        j.setIgnoreDefaultExcludes(false); //设置默认忽略
        j.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
       // PoConfig gridConfig = getPoConfig(ob.getClass());
      /*  if (gridConfig != null) {
            if (gridConfig.lazyField() != null && gridConfig.lazyField().length > 0) {
                j.setExcludes(gridConfig.lazyField());
            }
        }*/
        JSONObject jsonObj = JSONObject.fromObject(ob, j);
        return jsonObj.toString();
    }

    public static String JSON_List2String(Object ob) {
        JsonConfig j = new JsonConfig();
        j.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        j.setIgnoreDefaultExcludes(false); //设置默认忽略
        j.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
        JSONArray jsonArray = JSONArray.fromObject(ob, j);
        return jsonArray.toString();
    }

    public static String JSON_List2String(Object ob, String[] filterFields) {
        JsonConfig j = new JsonConfig();
        j.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        j.setIgnoreDefaultExcludes(false); //设置默认忽略
        j.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);   
        j.setExcludes(filterFields);
        JSONArray jsonArray = JSONArray.fromObject(ob, j);
        return jsonArray.toString();
    }

    public static Object JSON_String2Bean(String s, Class bean) {
        JSONObject jsonObj = JSONObject.fromObject(s);
        return JSONObject.toBean(jsonObj, bean);
    }

    public static List JSON_String2List(String s, Class bean) {
        JSONArray jsonArray = JSONArray.fromObject(s);
        List list = (List) JSONArray.toCollection(jsonArray, bean);
        return list;
    }

	
}
