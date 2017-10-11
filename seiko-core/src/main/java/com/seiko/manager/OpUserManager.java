/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: OpUserManagerImpl.java
 * Author:   admin
 * Date:     2016-03-10 14:19:08
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-10 14:19:08><version><desc><source>
 */

package com.seiko.manager;

import com.seiko.base.service.GenericManager;
import com.seiko.dao.OpUserDao;
import com.seiko.model.user.OpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementation of UserManager interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Transactional
@Service("opUserManager")
public class OpUserManager extends GenericManager<OpUser, Long> {
    private static final String DateUtil = null;
    private PasswordEncoder passwordEncoder;

    private OpUserDao opUserDao;

    @Autowired
    public void setOpUserDao(OpUserDao opUserDao) {
        this.dao = opUserDao;
        this.opUserDao = opUserDao;
    }

}
