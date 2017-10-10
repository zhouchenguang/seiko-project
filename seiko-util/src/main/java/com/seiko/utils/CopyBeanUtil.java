package com.seiko.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author ZHANGSHUAI009
 * @see[相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CopyBeanUtil {

    /**
     * 复制bean
     *
     * @param source
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> T copyBean(Object source, Class<T> classType) {
        T obj = null;
        if (source == null) {
            return null;
        }

        try {
            obj = classType.newInstance();
            BeanUtils.copyProperties(source, obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * 复制list
     *
     * @param source
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List source, Class<T> classType) {
        List<T> returnlist = new ArrayList<>();
        try {
            Integer size = source.size();
            for (int i = 0; i < size; i++) {
                T obj = classType.newInstance();
                BeanUtils.copyProperties(source.get(i), obj);
                returnlist.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return returnlist;
    }

    /**
     * copy bean 过滤掉空字段
     *
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }


    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
