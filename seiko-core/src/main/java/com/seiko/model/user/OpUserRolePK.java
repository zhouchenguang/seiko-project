package com.seiko.model.user;

import com.seiko.base.model.DynamicField;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class OpUserRolePK extends DynamicField implements Serializable {
    private static final long serialVersionUID = 3832626162173359411L;

    private Long userId;
    private Long roleId;

    @Column(name = "userId")
    @Id
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "roleId")
    @Id
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

}
