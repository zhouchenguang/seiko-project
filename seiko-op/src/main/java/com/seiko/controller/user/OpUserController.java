/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: IndexController.java
 * Author:   admin
 * Date:     2016-03-18 17:37:51
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述
 * <admin><2016-03-18 17:37:51><version><desc>
 */

package com.seiko.controller.user;


import com.alibaba.fastjson.JSONObject;
import com.seiko.base.constants.Constants;
import com.seiko.base.model.ReturnObject;
import com.seiko.controller.BaseController;
import com.seiko.model.OpUser;
import com.seiko.service.impl.OpUserService;
import com.seiko.service.qo.UserQO;
import com.seiko.utils.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 主页
 *
 * @author HUQIANBO
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/user")
public class OpUserController extends BaseController {

    private static Logger logger = LogManager.getLogger(OpUserController.class);

    // --------------------------- page ------------------------------------ //
    public static final String USER_LIST = "/user/user_list";//用户列表页面
    public static final String USER_ADD = "/user/user_add";//用户添加页面
    public static final String USER_EDIT = "/user/user_edit";//用户编辑页面
    // --------------------------- /page ------------------------------------ //

    // --------------------------- /service ------------------------------------ //
    @Autowired
    private OpUserService opUserService;
// --------------------------- /service ------------------------------------ //


    /**
     * 用户列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String roleList() {
        return USER_LIST;
    }

    /**
     * 用户列表查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/listJson", method = {RequestMethod.GET, RequestMethod.POST})
    public String listRole() {
        JSONObject json = new JSONObject();
        try {
            UserQO userQO = new UserQO();

            Integer page = Integer.parseInt(getP("page"));
            Integer pageSize = Integer.parseInt(getP("rows"));
            userQO.setPageNo(page);
            userQO.setPageSize(pageSize);
            ReturnObject<OpUser> ret = opUserService.searchOpUserList(userQO);
            List userList = ret.getDataList();
            Integer totalNo = ret.getTotalSize();

            json.put("total", totalNo);//分多少页
            json.put("rows", userList);
        } catch (Exception e) {
            json.put("total", 0);
            json.put("rows", new ArrayList<>());
            logger.error("获取用户列表异常", e);
        }
        return json.toString();
    }

    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String add(Model model) {
        logger.info("call saveUser({})");
        JSONObject json = new JSONObject();
        return json.toString();
    }

    /**
     * 修改商品页
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    @ResponseBody
    public String edit(Long id) {
        logger.info("call edit({})", id);
        JSONObject json = new JSONObject();
        try {
            if (null != id) {
                //查询用户
                UserQO userQO = new UserQO();
                userQO.setUserId(id);
                ReturnObject<OpUser> returnObject = opUserService.getOpUser(userQO);
                if (Constants.RETURN_CODE_SUCCESS.equals(returnObject.getReturnCode())) {
                    json.put("data", returnObject.getData());
                    json.put("result", Constants.RETURN_CODE_SUCCESS.equals(returnObject.getReturnCode()));
                }
            }

        } catch (Exception e) {
            logger.error("保存用户异常", e);
            json.put("result", false);
        }

        return json.toString();
    }

    /**
     * 保存用户
     *
     * @param opUser
     * @return
     */
    @RequestMapping(value = "/saveUser", method = {RequestMethod.POST})
    @ResponseBody
    public String saveUser(OpUser opUser) {
        logger.info("call saveUser({})", JsonUtil.JSON_Bean2String(opUser));
        JSONObject json = new JSONObject();
        try {

            String operName = getOperatorInfo().getOperatorName();
            if (null == opUser.getUserId()) {
                opUser.setCreateTime(new Date());
                opUser.setCreateUser(operName);
            }
            opUser.setUpdateTime(new Date());
            opUser.setUpdateUser(operName);

            ReturnObject<OpUser> returnObject = opUserService.saveOpUser(opUser);
//            json.put("data", returnObject.getData());
            json.put("result", Constants.RETURN_CODE_SUCCESS.equals(returnObject.getReturnCode()));
        } catch (Exception e) {
            logger.error("保存用户异常", e);
            json.put("result", false);
        }

        return json.toString();
    }
}
