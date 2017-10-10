/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: GenericManagerImpl.java
 * Author:   admin
 * Date:     2016-03-08 15:31:50
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-08 15:31:50><version><desc><source>
 *
 */

package com.seiko.base.service;

import com.seiko.base.dao.GenericDao;
import com.seiko.base.model.QueryModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="com.jisj.manager.impl.GenericManager"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.jisj.dao.hibernate.GenericDao"&gt;
 *                 &lt;constructor-arg value="com.jisj.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="com.jisj.manager.impl.GenericManager"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.jisj.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="com.jisj.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * Updated by jgarcia: added full text search + reindexing
 */
public class GenericManager<T, PK extends Serializable> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * GenericDao instance, set by constructor of child classes
     */
    protected GenericDao<T, PK> dao;


    public GenericManager() {
    }

    public GenericManager(GenericDao<T, PK> genericDao) {
        this.dao = genericDao;
    }

    //项目配置属性
    protected Properties projectProperties;

    @Resource
    public void setProjectProperties(Properties projectProperties) {
        this.projectProperties = projectProperties;
    }


    /**
     * {@inheritDoc}
     */
    public List<T> getAll() {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public T get(PK id) {
        return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    public T save(T object) {
        return dao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        dao.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        dao.remove(id);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Search implementation using Hibernate Search.
     */
    @SuppressWarnings("unchecked")
    public List<T> search(String q, Class clazz) {
        if (q == null || "".equals(q.trim())) {
            return getAll();
        }

        return dao.search(q);
    }

    /**
     * {@inheritDoc}
     */
    public void reindex() {
        dao.reindex();
    }

    /**
     * {@inheritDoc}
     */
    public void reindexAll(boolean async) {
        dao.reindexAll(async);
    }

    public List<T> search(QueryModel queryModel) {
        return dao.search(queryModel);
    }

    public T getUniqueResult(QueryModel queryModel) {
        List<T> list = dao.search(queryModel);
        T obj = null;
        if (!list.isEmpty()) {
            obj = list.get(0);
        }

        return obj;
    }

}
