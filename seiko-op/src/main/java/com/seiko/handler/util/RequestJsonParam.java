package com.seiko.handler.util;

import java.lang.annotation.*;

/**
 * Created by HUQIANBO001 on 2016/3/19.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJsonParam {

    /**
     * 用于绑定的请求参数名字
     */
    String value() default "" ;
    /**
     * 是否必须，默认是
     */
    boolean required() default true;

}
