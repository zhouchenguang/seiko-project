package com.seiko.base;

import java.io.Serializable;

/**
 * Created by chenqm on 2016/3/25.
 */
public enum SortDirection implements Serializable {

    ASC("ASC"),
    DESC("DESC");

    private String code;

    public String getCode() {
        return this.code;
    }

    SortDirection(String code) {
        this.code = code;
    }

    public String toString() {
        return this.name();
    }


}
