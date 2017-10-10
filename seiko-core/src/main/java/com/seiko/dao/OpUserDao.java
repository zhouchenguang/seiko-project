/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: OpUserDaoHibernate.java
 * Author:   admin
 * Date:     2016-03-10 14:04:22
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-10 14:04:22><version><desc><source>
 */

package com.seiko.dao;

import com.seiko.base.dao.GenericDao;
import com.seiko.model.OpUser;
import org.springframework.stereotype.Repository;


/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve User objects.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * Modified by <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 * Extended to implement Acegi UserDetailsService interface by David Carter david@carter.net
 * Modified by <a href="mailto:bwnoll@gmail.com">Bryan Noll</a> to work with
 * the new BaseDaoHibernate implementation that uses generics.
 * Modified by jgarcia (updated to hibernate 4)
 */
@Repository("opUserDao")
public class OpUserDao extends GenericDao<OpUser, Long> {

    /**
     * Constructor that sets the entity to User.class.
     */
    public OpUserDao() {
        super(OpUser.class);
    }


}
