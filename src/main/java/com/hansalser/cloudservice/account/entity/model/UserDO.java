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
public class UserDO {

    // 用户状态：有效
    public static final Integer STATUS_VALID = 1;
    // 用户状态：锁定
    public static final Integer STATUS_LOCK = 0;
    // 默认头像
    public static final String DEFAULT_AVATAR = "default.jpg";
    // 默认密码
    public static final String DEFAULT_PASSWORD = "1234qwer";
    // 性别男
    public static final Integer SEX_MALE = 0;
    // 性别女
    public static final Integer SEX_FEMALE = 1;
    // 性别保密
    public static final Integer SEX_UNKNOW = 2;
    // 黑色主题
    public static final String THEME_BLACK = "black";
    // 白色主题
    public static final String THEME_WHITE = "white";
    // TAB开启
    public static final Integer TAB_OPEN = 1;
    // TAB关闭
    public static final Integer TAB_CLOSE = 0;

    @TableId("id")
    private Integer id;

    @TableField("user_id")
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("dept_id")
    private Long deptId;

    @TableField("email")
    private String email;

    @TableField("mobile")
    private String mobile;

    @TableField("sex")
    private Integer sex;

    @TableField("is_tab")
    private Integer isTab;

    @TableField("status")
    private Integer status;

    @TableField("theme")
    private String theme;

    @TableField("avatar")
    private String avatar;

    @TableField("description")
    private String description;

    @TableField("last_login_time")
    private Date lastLoginTime;

    @TableField("craete_at")
    private Date createAt;

    @TableField("update_at")
    private Date updateAt;

    private String deptName;

    private String createTimeFrom;

    private String createTimeTo;

    private String roleId;

    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getIsTab() {
        return isTab;
    }

    public void setIsTab(Integer isTab) {
        this.isTab = isTab;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(String createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public String getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(String createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
