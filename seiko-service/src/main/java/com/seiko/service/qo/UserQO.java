package com.seiko.service.qo;

import com.seiko.base.PageCondition;

/**
 * Created by cqm on 2017/4/19.
 */
public class UserQO extends PageCondition {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
