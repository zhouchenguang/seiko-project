package com.seiko.handler;

import com.alibaba.fastjson.JSON;
import com.seiko.handler.util.MediaTypes;
import com.seiko.handler.util.Reflections;
import com.seiko.handler.util.RequestJsonParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

/**
 * Created by HUQIANBO001 on 2016/3/19.
 */
public class RequestJsonHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static Logger logger = LoggerFactory.getLogger(RequestJsonHandlerMethodArgumentResolver.class);


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestJsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestJsonParam requestJsonParam = parameter.getParameterAnnotation(RequestJsonParam.class);

        final HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        String requestBody = getRequestBody(servletRequest.getInputStream());
        // content-type不是json的不处理
        if (!servletRequest.getContentType().contains(MediaTypes.JSON) || StringUtils.isBlank(requestBody)) {
            logger.error("请求类型错误或者RequestBody为空");
            return null;
        }

        String aliasName = getAlias(requestJsonParam, parameter);
        // 利用fastjson转换为对应的类型
        if (Long.class.isAssignableFrom(parameter.getParameterType())) {
            return JSON.parseObject(requestBody).getLong(aliasName);
        } else if (String.class.isAssignableFrom(parameter.getParameterType())) {
            return JSON.parseObject(requestBody).getString(aliasName);
        } else if (Integer.class.isAssignableFrom(parameter.getParameterType())) {
            return JSON.parseObject(requestBody).getInteger(aliasName);
        } else if (Boolean.class.isAssignableFrom(parameter.getParameterType())) {
            return JSON.parseObject(requestBody).getBoolean(aliasName);
        } else if (Byte.class.isAssignableFrom(parameter.getParameterType())) {
            return JSON.parseObject(requestBody).getByte(aliasName);
        } else if (Short.class.isAssignableFrom(parameter.getParameterType())) {
            return JSON.parseObject(requestBody).getShort(aliasName);
        } else if (Double.class.isAssignableFrom(parameter.getParameterType())) {
            return JSON.parseObject(requestBody).getDouble(aliasName);
        } else if (Float.class.isAssignableFrom(parameter.getParameterType())) {
            return JSON.parseObject(requestBody).getFloat(aliasName);
        } else if (BigDecimal.class.isAssignableFrom(parameter.getParameterType())) {
            return JSON.parseObject(requestBody).getBigDecimal(aliasName);
        } else {
            String innerParam = JSON.parseObject(requestBody).getString(aliasName);
            if (Collection.class.isAssignableFrom(parameter.getParameterType())) {
                //获取实际类型
                Class clazz = Reflections.getClassGenricType(parameter.getGenericParameterType());
                return JSON.parseArray(innerParam, clazz);
            }
            return JSON.parseObject(innerParam, parameter.getParameterType());
        }
    }

    /**
     * 复用流的内容
     *
     * @param inputStream
     * @return
     * @throws java.io.IOException
     */
    private String getRequestBody(InputStream inputStream) throws IOException {
        String requestBody = RequestBodyThreadLocalInterceptor.RequestBodyThreadLocal.get();
        if (StringUtils.isBlank(requestBody)) {
            requestBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            RequestBodyThreadLocalInterceptor.RequestBodyThreadLocal.set(requestBody);
        }
        return requestBody;
    }

    /**
     * 获取别名
     *
     * @param requestJsonParam
     * @param parameter
     * @return
     */
    private String getAlias(RequestJsonParam requestJsonParam, MethodParameter parameter) {
        String alias = requestJsonParam.value();
        if (StringUtils.isBlank(alias)) {
            alias = parameter.getParameterName();
        }
        return alias;
    }
}
