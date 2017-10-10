package com.seiko.base;


import java.io.Serializable;

/**
 * Created by chenqm on 2016/3/25.
 */
public class PageCondition implements Serializable {

    private Integer pageNo; //当前页码
    private Integer pageSize; //每页显示数量
    private String sortBy; //排序字段
    private SortDirection direction; //升序还是降序
    private String order;//升序还是降序,添加冗余字段，防止前台json无法转换

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

}
