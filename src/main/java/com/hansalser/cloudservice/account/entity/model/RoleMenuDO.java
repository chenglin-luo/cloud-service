package com.hansalser.cloudservice.account.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author chenglin.luo
 * @date 2019/6/23 16:58
 */
@TableName("t_dept")
public class RoleMenuDO {

    @TableField("role_id")
    private Long roleId;

    @TableField("menu_id")
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
