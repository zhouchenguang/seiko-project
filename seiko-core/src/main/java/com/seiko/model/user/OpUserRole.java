package com.seiko.model.user;

import com.seiko.base.model.DynamicField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(OpUserRolePK.class)
public class OpUserRole extends DynamicField implements Serializable {
    private static final long serialVersionUID = 3832626162173359411L;

    private Long userId;
    private Long roleId;

    @Id
    @Column(name = "userId")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "roleId")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

}
