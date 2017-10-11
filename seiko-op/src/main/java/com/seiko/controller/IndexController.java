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

import com.seiko.service.impl.OpUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页
 *
 * @author HUQIANBO
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class IndexController extends BaseController {

    private static Logger logger = LogManager.getLogger(IndexController.class);

    // --------------------------- page ------------------------------------ //
    public static final String INDEX_PAGE = "main_page"; //网站首页
    // --------------------------- /page ------------------------------------ //

    // --------------------------- service ------------------------------------ //
    @Autowired
    private OpUserService opUserService;
    // --------------------------- /service ------------------------------------ //

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    @ResponseBody
    public ModelAndView index() {
        logger.info("call IndexController.index()");
        ModelAndView modelAndView = new ModelAndView(INDEX_PAGE);
        try {
//            ReturnObject<OpUser> returnObject = opUserService.getOpUser();
//            if (Constants.RETURN_CODE_SUCCESS.equals(returnObject.getReturnCode())) {
//                OpUser opUser = returnObject.getData();
//                modelAndView.addObject("opUser", opUser);
//            }
        } catch (Exception e) {
            logger.error("IndexController.index error", e);
        }
        return modelAndView;
    }

}
