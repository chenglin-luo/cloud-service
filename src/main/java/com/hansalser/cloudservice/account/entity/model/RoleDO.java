package com.hansalser.cloudservice.account.entity.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author chenglin.luo
 * @date 2019/6/23 16:57
 */
@TableName("t_dept")
public class RoleDO {

    @TableId("id")
    private Integer id;

    @TableField("role_id")
    private Long roleId;

    @TableField("role_name")
    private String roleName;

    @TableField("remark")
    private String remark;

    @TableField("craete_at")
    private Date createAt;

    @TableField("update_at")
    private Date updateAt;

    /**
     * 角色对应的菜单（按钮） id
     */
    private transient String menuIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
