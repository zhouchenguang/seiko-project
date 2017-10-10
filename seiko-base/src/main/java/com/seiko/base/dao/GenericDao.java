/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: GenericDaoHibernate.java
 * Author:   admin
 * Date:     2016-03-08 15:32:23
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-08 15:32:23><version><desc><source>
 *
 */

package com.seiko.base.dao;

import com.seiko.base.model.QueryModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.*;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.jisj.dao.hibernate.GenericDao"&gt;
 *          &lt;constructor-arg value="com.jisj.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * Updated by jgarcia: update hibernate3 to hibernate4
 * @author jgarcia (update: added full text search + reindexing)
 */
public class GenericDao<T, PK extends Serializable> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;
    @Resource
    private SessionFactory sessionFactory;
    private Analyzer defaultAnalyzer;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDao(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDao(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
        defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session sess = getSession();
        return sess.createCriteria(persistentClass).list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> search(String searchTerm) throws SearchException {
        Session sess = getSession();
        FullTextSession txtSession = Search.getFullTextSession(sess);

        org.apache.lucene.search.Query qry;
        try {
            qry = HibernateSearchTools.generateQuery(searchTerm, this.persistentClass, sess, defaultAnalyzer);
        } catch (ParseException ex) {
            throw new SearchException(ex);
        }
        org.hibernate.search.FullTextQuery hibQuery = txtSession.createFullTextQuery(qry,
                this.persistentClass);
        return hibQuery.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        Session sess = getSession();
        return (T) sess.merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        Session sess = getSession();
        sess.delete(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        sess.delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Session sess = getSession();
        Query namedQuery = sess.getNamedQuery(queryName);

        for (String s : queryParams.keySet()) {
            namedQuery.setParameter(s, queryParams.get(s));
        }

        return namedQuery.list();
    }

    /**
     * {@inheritDoc}
     */
    public void reindex() {
        HibernateSearchTools.reindex(persistentClass, getSessionFactory().getCurrentSession());
    }


    /**
     * {@inheritDoc}
     */
    public void reindexAll(boolean async) {
        HibernateSearchTools.reindexAll(async, getSessionFactory().getCurrentSession());
    }

    public List<T> search(QueryModel queryModel) {

        String hql = queryModel.getQueryHqlStr();
        Session session = getSessionFactory().getCurrentSession();
        try {


            boolean pageFlag = false;
            Boolean isIncludeWhere = queryModel.getIncludeWhere();

            String whereWord = " and ";
            if (!isIncludeWhere) {
                whereWord = " where ";
            }

            //如果在分页模式下，则计算总数
            if (queryModel.getPageSize() != null && queryModel.getTotalNum() != null) {
                String countSql = "";

                if (queryModel.getSelectCountSqlHead() != null && !"".equals(queryModel.getSelectCountSqlHead())) {
                    countSql = queryModel.getSelectCountSqlHead() + whereWord + hql;
                } else {
                    countSql = "select count(*) from " + this.persistentClass.getName();
                    if (hql != null) countSql = countSql + whereWord + hql;
                }

                Query countQuery = session.createQuery(countSql);
                countQuery.setCacheable(true);
                queryModel.assignValue(countQuery);
                List list = countQuery.list();
                int totalNum = 0;
                if (!list.isEmpty()) {
                    totalNum = ((Long) list.get(0)).intValue();
                }

                queryModel.setTotalNum(totalNum);
                pageFlag = true;
            }


            String queryString = "";
            if (queryModel.getSelectSqlHead() != null && !"".equals(queryModel.getSelectSqlHead())) {
                queryString = queryModel.getSelectSqlHead() + whereWord + hql;
            } else {

                queryString = " from " + this.persistentClass.getName();
                if (hql != null) queryString = queryString + whereWord + hql;

            }

            if (queryModel.getSortHql() != null && !"".equals(queryModel.getSortHql())) {
                queryString = queryString + " order by " + queryModel.getSortHql();
            }
            Query query = session.createQuery(queryString);
            query.setCacheable(true);
            queryModel.assignValue(query);

            List list = null;

            //分页模式下只列当前页
            if (queryModel.getPageSize() != null) {
                list = query.setFirstResult(queryModel.getStartRecord()).setMaxResults(queryModel.getPageSize()).list();

            } else {
                list = query.list();

            }

            if (!pageFlag) {

                if (!list.isEmpty()) {
                    queryModel.setTotalNum(list.size());
                } else {
                    queryModel.setTotalNum(0);
                }
            }

            return list;
        } catch (RuntimeException re) {
            re.printStackTrace();
            log.error("find by property name failed", re);
            throw re;
        }

    }

    public List searchObject(String sql, Class clazz) {
        Session sess = getSession();
        //Transaction trans = sess.beginTransaction();
        List list = new ArrayList();

        try {
            SQLQuery query = sess.createSQLQuery(sql);
            query.addEntity(clazz);
            list = query.list();
            //trans.commit();
        } catch (Exception e) {
            //trans.rollback();
            e.printStackTrace();
        }

        return list;
    }


    public Object searchObject(String sql) {
        Session sess = getSession();
        //Transaction trans = sess.beginTransaction();
        Object obj = null;
        try {
            SQLQuery query = sess.createSQLQuery(sql);
            obj = query.uniqueResult();
            //str = query.getQueryString();
            //trans.commit();
        } catch (Exception e) {
            //trans.rollback();
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * 执行uodate语句
     *
     * @param sql
     * @return count
     */
    public int updateObject(String sql) {
        Session sess = getSession();
        //Transaction trans = sess.beginTransaction();
        int count = 0;
        try {
            SQLQuery query = sess.createSQLQuery(sql);
            count = query.executeUpdate();
            //trans.commit();
        } catch (Exception e) {
            //trans.rollback();
            e.printStackTrace();
        }

        return count;
    }

    public Map<String, Object> searchMap(String sql) {
        Session sess = getSession();
        Transaction trans = null;
        Map<String, Object> obj = null;
        try {
            SQLQuery query = sess.createSQLQuery(sql);
            obj = (Map<String, Object>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            flushSession();
        }

        return obj;

    }

    public List<Map<String, Object>> searchMapList(String sql) {
        Session sess = getSession();
        List<Map<String, Object>> obj = null;
        try {
            SQLQuery query = sess.createSQLQuery(sql);
            obj = (List) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            flushSession();
        }

        return obj;
    }

    public void flushSession() {
        Session session = getSession();
        if (session != null) {
            session.flush();
        }
    }
}
