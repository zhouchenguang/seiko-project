/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: IndexController.java
 * Author:   admin
 * Date:     2016-03-18 17:37:51
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述
 * <admin><2016-03-18 17:37:51><version><desc>
 */

package com.seiko.controller;

import com.seiko.base.model.OperatorInfo;
import com.seiko.controller.utils.Helper;
import com.seiko.utils.ParamsFilterUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author HUQIANBO
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class BaseController {

    private static Logger logger = LogManager.getLogger(BaseController.class);

    @Value("#{configProperties['upload.static']}")
    private String UPLOAD_STATIC;//文件上传group

    @Value("#{configProperties['activitiRestUrl']}")
    protected String ACTIVITI_RESTURL; //审批流请求ip地址

    // ------------------------- service------------------------------------- //

    protected HttpServletRequest getRequest() {
        return Helper.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return Helper.getResponse();
    }

    protected String getP(String key) {
        return ParamsFilterUtil.cleanXSS(getRequest().getParameter(key));
    }

    protected String[] getPs(String key) {
        String[] parameters = getRequest().getParameterValues(key);
        if (parameters != null && parameters.length > 0) {
            for (String str : parameters) {
                str = ParamsFilterUtil.cleanXSS(str);
            }
        }
        return parameters;
    }

    protected boolean validCodes(String key, String validcode) {
        String code = (String) getRequest().getSession().getAttribute(key);
        if (code != null && validcode != null) {
            if (code.contains(",")) {
                String[] codes = code.split(",");
                code = codes[0];
                Long time = Long.valueOf(Long.parseLong(codes[1]));
                Long nowtime = Long.valueOf((new Date()).getTime());
                if (nowtime.longValue() - time.longValue() > 600000L) {
                    return false;
                }
            }

            if (code.equalsIgnoreCase(validcode)) {
                getRequest().getSession().removeAttribute(key);
                return true;
            }
        }

        return false;
    }

    /**
     * 获取当前登录人信息
     *
     * @return
     */
    public OperatorInfo getOperatorInfo() {
        OperatorInfo operatorInfo = new OperatorInfo();
        try {
        } catch (Exception e) {
            logger.error("获取当前用户信息失败", e);
        }
        return operatorInfo;
    }

    /**
     * 获取绝对路径
     *
     * @return
     */
    public String getPath() {
        return this.getClass().getClassLoader().getResource("").getPath();
    }

}
