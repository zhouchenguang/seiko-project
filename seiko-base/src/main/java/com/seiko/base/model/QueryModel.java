/*
 * Copyright (C),2016-2016. 华住酒店管理有限公司
 * FileName: QueryModel.java
 * Author:   admin
 * Date:     2016-03-08 15:32:43
 * Description: //模块目的、功能描述
 * History: //修改记录 修改人姓名 修改时间 版本号 描述 需求来源
 * <admin><2016-03-08 15:32:43><version><desc><source>
 *
 */

package com.seiko.base.model;

import org.hibernate.Query;

import java.util.*;

/**
 * hibernate查询辅助类
 *
 * @author Administrator
 */
public class QueryModel {

    private Integer pageSize;   //分页中查询每页大小

    private Integer pageNo;     //当请那一页

    private Integer totalNum;   //总条数    

    private List conditionList = new ArrayList();

    private List resultList;    //查询结果

    private Integer resultSize;    //当前结果数量

    private Boolean queryFlag;   //true do query totalNum,false, not query;

    private String sortHql;

    private String selectSqlHead;     //查询语句头,为支持灵活的，个性化Sql

    private String selectCountSqlHead;  //计算查询结果数,为支持灵活的，个性化Sql

    private Boolean includeWhere = false;   //是否包含Where关键字

    private int startRecorder;  //从第一条开始

    private Integer totalPageNum;   //总页数

    public int getStartRecorder() {
        return startRecorder;
    }


    public void setStartRecorder(int startRecorder) {
        this.startRecorder = startRecorder;
    }


    public String getSortHql() {
        return sortHql;
    }


    public void setSortHql(String sortHql) {
        this.sortHql = sortHql;
    }


    public String getSelectSqlHead() {
        return selectSqlHead;
    }


    public void setSelectSqlHead(String selectSqlHead, Boolean includeWhere) {
        this.selectSqlHead = selectSqlHead;
        this.includeWhere = includeWhere;
    }


    public Boolean getIncludeWhere() {
        return includeWhere;
    }


    public String getSelectCountSqlHead() {
        return selectCountSqlHead;
    }


    public void setSelectCountSqlHead(String selectCountSqlHead) {
        this.selectCountSqlHead = selectCountSqlHead;
    }

    //拼接sql条件如：XX=值
    public void addColumnValueCondition(String columnName, Object columnValue) {
        addColumnValueCondition(columnName, QueryModel.OPERATOR_EQ, columnValue);
    }

    public void addColumnValueCondition(String columnName, String operator, Object columnValue) {
        if (columnValue != null && !columnValue.equals("")) {

            String valueStr = String.valueOf(columnValue);
            if (QueryModel.OPERATOR_LIKE.equals(operator)) {
                valueStr = "%" + columnValue + "%";
            } else if (QueryModel.OPERATOR_LEFT_LIKE.equals(operator)) {
                valueStr = columnValue + "%";
            } else if (QueryModel.OPERATOR_RIGHT_LIKE.equals(operator)) {
                valueStr = "%" + columnValue;
            }
            Condition condition = new Condition(columnName, operator, valueStr);

            conditionList.add(condition);
        }
    }

    /**
     * 使用在 colName is null 或者 colName = '1' 等固定条件的场合
     *
     * @param hql
     */
    public void addHqlCondition(String hql) {
        Condition condition = new Condition(hql);
        conditionList.add(condition);
    }


    /**
     * 参数绑定hql查询方式,最终将所有条件组合成一个hql语句
     *
     * @param oneHqlFilter 内含操作符号可以是 = like 等hql支持的操作符号 例如 personName = :personName
     * @param paraName
     * @param paraValue
     */
    public void addParamBindCondition(String oneHqlFilter, String paraName, Object paraValue, String hqlValueStr) {

        if (paraValue != null && !paraValue.equals("")) {
            if (hqlValueStr != null && !"".equals(hqlValueStr)) paraValue = hqlValueStr;
            Condition condition = new Condition(oneHqlFilter, paraName, "", paraValue);
            conditionList.add(condition);
        }

    }


    /**
     * 参数绑定hql查询方式,最终将所有条件组合成一个hql语句
     *
     * @param oneHqlFilter
     * @param paraName
     * @param paraValue
     */
    public void addParamBindCondition(String oneHqlFilter, String paraName, Object paraValue) {
        addParamBindCondition(oneHqlFilter, paraName, paraValue, "");
    }


    /**
     * 拼hql查询条件
     *
     * @return
     */
    public String getQueryHqlStr() {
        String hql = " 1=1 ";
        Iterator it = conditionList.iterator();
        int i = 0;
        while (it.hasNext()) {

            Condition condition = (Condition) it.next();
            //if(i==0 ) hql = condition.getConditionStr();
            //else
            hql = hql + " and " + condition.getConditionStr();
            i++;

        }
        return hql;

    }

    /**
     * 绑定参数值
     *
     * @param query
     */
    public void assignValue(Query query) {

        String hql = "";
        Iterator it = conditionList.iterator();

        while (it.hasNext()) {
            Condition condition = (Condition) it.next();

            if (condition.getParamValue() != null) {
                String type = condition.getParamValue().getClass().getName();
                if (condition.getParamValue() instanceof String) {
                    query.setString(condition.getParamName(), String.valueOf(condition.getParamValue()));
                } else if (condition.getParamValue() instanceof Date) {
                    Calendar calEnd = Calendar.getInstance();
                    calEnd.setTime((Date) condition.getParamValue());

                    query.setCalendar(condition.getParamName(), calEnd);
                } else {
                    query.setString(condition.getParamName(), String.valueOf(condition.getParamValue()));
                }


            }

        }

    }


    public int getStartRecord() {
        return (pageNo - 1) * pageSize;

    }


    public final static String AND = "and";

    public final static String OR = "or";

    public final static String NOT = "not";

    public final static String IS = "is";

    public final static String ISNOT = "is not";

    public final static String ISNOTNULL = "is not null";

    public final static String OPERATOR_EQ = "=";

    public final static String OPERATOR_NE = "!=";

    public final static String OPERATOR_NE_ANSINULL_OFF = "!=%";

    public final static String OPERATOR_GE = ">=";

    public final static String OPERATOR_GT = ">";

    public final static String OPERATOR_NGE = "!>=";

    public final static String OPERATOR_NGT = "!>";

    public final static String OPERATOR_LE = "<=";

    public final static String OPERATOR_LT = "<";

    public final static String OPERATOR_NLE = "!<=";

    public final static String OPERATOR_NLT = "!<";

    public final static String OPERATOR_LIKE = "like";

    public final static String OPERATOR_NLIKE = "!like";

    public final static String OPERATOR_LEFT_LIKE = "leftlike";

    public final static String OPERATOR_RIGHT_LIKE = "rightlike";

    public final static String OPERATOR_INCLUDE = "include";

    public final static String OPERATOR_NINCLUDE = "!include";

    public final static String OPERATOR_ILIKE = "like";

    public final static String OPERATOR_NILIKE = "!ilike";

    public final static String OPERATOR_IINCLUDE = "iinclude";

    public final static String OPERATOR_NIINCLUDE = "!iinclude";

    public final static String OPERATOR_IS = "is";

    public final static String OPERATOR_NIS = "!is";

    public final static String OPERATOR_IN = "in";

    public final static String OPERATOR_NIN = "not in";

    public final static String FETCH = "fetch";

    public final static int BASIC = 1;

    public final static int ADVANCED = 2;


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List getConditionList() {
        return conditionList;
    }

    public void setConditionList(List conditionList) {
        this.conditionList = conditionList;
    }

    public Integer getTotalNum() {
        if (totalNum == null) totalNum = 0;
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }


    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

    public int getResultSize() {
        return resultSize;
    }

    public void setResultSize(Integer resultSize) {
        this.resultSize = resultSize;
    }

    public boolean isQueryFlag() {
        return queryFlag;
    }

    public void setQueryFlag(boolean queryFlag) {
        this.queryFlag = queryFlag;
    }


    public Integer getTotelPageNum() {
        if(pageSize!=null && totalNum!=null){
            totalPageNum = totalNum / pageSize;
            if (totalNum % pageSize > 0 || totalNum == 0) {
                totalPageNum += 1;
            }
        }else {
            totalPageNum = 0;
        }
        return totalPageNum;
    }

    public void setTotelPageNum(Integer totelPageNum) {
        this.totalPageNum = totelPageNum;
    }
}

class Condition {

    public static final int QUERY_TYPE_ONE = 1;

    public static final int QUERY_TYPE_TWO = 2;

    public static final int QUERY_TYPE_THREE = 3;


    //方式一：字段，操作符号，值
    private String columnName;      //绑定变量名

    private String operator;      //操作符号

    private Object columnValue;     //变量值


    //方式二： 直接SQL语句

    private String conditionHql;        //Hql查询条件


    //方式三：参数名称，值

    private String paramName;

    private Object paramValue;

    private String paramConditionHql;


    private boolean emptyExclude; //去除空值

    private int queryType;      //查询方式


    public Condition(String columnName, String operator, Object columnValue) {
        super();
        this.columnName = columnName;
        this.operator = operator;
        this.columnValue = columnValue;
        this.queryType = Condition.QUERY_TYPE_ONE;

    }


    public Condition(String conditionHql) {
        super();
        this.conditionHql = conditionHql;
        this.queryType = Condition.QUERY_TYPE_TWO;
    }


    public Condition(String paramConditionHql, String paramName, String operator, Object paramValue) {
        super();
        this.operator = operator;
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.paramConditionHql = paramConditionHql;
        this.queryType = Condition.QUERY_TYPE_THREE;
    }


    public String getConditionStr() {
        String conditionStr = "";
        if (this.queryType == Condition.QUERY_TYPE_ONE) {
            if (QueryModel.ISNOTNULL.equals(operator)) {
                conditionStr = " " + columnName + " " + columnValue;
            } else if (QueryModel.OPERATOR_IN.equals(operator) || QueryModel.OPERATOR_NIN.equals(operator)) {
                conditionStr = " " + columnName + " " + operator + " " + columnValue;
            } else {
                conditionStr = " " + columnName + " " + operator + " '" + columnValue + "' ";
            }

        } else if (this.queryType == Condition.QUERY_TYPE_TWO) {
            conditionStr = this.conditionHql;
        } else if (this.queryType == Condition.QUERY_TYPE_THREE) {
            conditionStr = this.paramConditionHql;
        } else {

        }

        return conditionStr;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(Object columnValue) {
        this.columnValue = columnValue;
    }

    public String getConditionHql() {
        return conditionHql;
    }

    public void setConditionHql(String conditionHql) {
        this.conditionHql = conditionHql;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Object getParamValue() {
        return paramValue;
    }

    public void setParamValue(Object paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamConditionHql() {
        return paramConditionHql;
    }

    public void setParamConditionHql(String paramConditionHql) {
        this.paramConditionHql = paramConditionHql;
    }

    public boolean isEmptyExclude() {
        return emptyExclude;
    }

    public void setEmptyExclude(boolean emptyExclude) {
        this.emptyExclude = emptyExclude;
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }


}
 
