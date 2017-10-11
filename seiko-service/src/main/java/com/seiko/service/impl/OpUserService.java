/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: OpUserServiceImpl.java
 * Author:   admin
 * Date:     2016-03-10 16:05:31
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-10 16:05:31><version><desc><source>
 */

package com.seiko.service.impl;

import com.seiko.base.constants.Constants;
import com.seiko.base.model.QueryModel;
import com.seiko.base.model.ReturnObject;
import com.seiko.manager.OpUserManager;
import com.seiko.model.user.OpUser;
import com.seiko.service.qo.UserQO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author admin
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service("opUserService")
public class OpUserService {

    private static Logger logger = LogManager.getLogger(OpUserService.class);

    @Autowired
    private OpUserManager opUserManager;


    /**
     * 查询op用户
     *
     * @return
     */
    public ReturnObject<OpUser> searchOpUserList(UserQO userQO) {
        ReturnObject<OpUser> returnObject = new ReturnObject<OpUser>();
        try {
            logger.info("call searchOpUserList");
            QueryModel queryModel = new QueryModel();

            queryModel.setPageNo(userQO.getPageNo());
            queryModel.setPageSize(userQO.getPageSize());
            queryModel.setSortHql(" updateTime desc");
            List<OpUser> opUserList = opUserManager.search(queryModel);
            returnObject.setTotalSize(queryModel.getTotalNum());
            returnObject.setDataList(opUserList);
            returnObject.setReturnCode(Constants.RETURN_CODE_SUCCESS);
        } catch (Exception e) {
            returnObject.setReturnCode(Constants.RETURN_CODE_ERROR);
            returnObject.setReturnMsg("查询失败");
            logger.error("call searchOpUserList error", e);
        }

        return returnObject;
    }

    /**
     * 查询op用户
     *
     * @return
     */
    public ReturnObject<OpUser> getOpUser(UserQO userQO) {
        ReturnObject<OpUser> returnObject = new ReturnObject<OpUser>();
        try {
            logger.info("call getOpUser");
            if (null == userQO.getUserId()) {
                returnObject.setReturnCode(Constants.RETURN_CODE_ERROR);
                returnObject.setReturnMsg("用户id不能为空");
                return returnObject;
            }
            OpUser opUser = opUserManager.get(userQO.getUserId());
            returnObject.setData(opUser);
            returnObject.setReturnCode(Constants.RETURN_CODE_SUCCESS);
        } catch (Exception e) {
            returnObject.setReturnCode(Constants.RETURN_CODE_ERROR);
            returnObject.setReturnMsg("查询失败");
            logger.error("call getOpUser error", e);
        }

        return returnObject;
    }

    /**
     * 查询op用户
     *
     * @return
     */
    public ReturnObject<OpUser> saveOpUser(OpUser opUser) {
        ReturnObject<OpUser> returnObject = new ReturnObject<OpUser>();
        try {
            logger.info("call saveOpUser");
            if (null == opUser) {
                returnObject.setReturnCode(Constants.RETURN_CODE_ERROR);
                returnObject.setReturnMsg("用户信息不能为空");
                return returnObject;
            }
            opUserManager.save(opUser);
            returnObject.setData(opUser);
            returnObject.setReturnCode(Constants.RETURN_CODE_SUCCESS);
        } catch (Exception e) {
            returnObject.setReturnCode(Constants.RETURN_CODE_ERROR);
            returnObject.setReturnMsg("保持失败");
            logger.error("call saveOpUser error", e);
        }

        return returnObject;
    }
}
