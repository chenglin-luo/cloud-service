package com.hansalser.cloudservice.account.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author chenglin.luo
 * @date 2019/6/23 16:58
 */
@TableName("t_dept")
public class UserRoleDO {

    @TableField("role_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
