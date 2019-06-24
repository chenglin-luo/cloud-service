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
public class DeptDO {

    @TableId("id")
    private Integer id;

    @TableField("dept_id")
    private Long deptId;

    @TableField("parent_id")
    private Long parentId;

    @TableField("dept_name")
    private String deptName;

    @TableField("dept_order")
    private Integer deptOrder;

    @TableField("craete_at")
    private Date createAt;

    @TableField("update_at")
    private Date updateAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptOrder() {
        return deptOrder;
    }

    public void setDeptOrder(Integer deptOrder) {
        this.deptOrder = deptOrder;
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
}
