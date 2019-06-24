package com.hansalser.cloudservice.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hansalser.cloudservice.Common.authentication.ShiroRealm;
import com.hansalser.cloudservice.Common.entity.CloudServiceConstant;
import com.hansalser.cloudservice.Common.entity.QueryRequest;
import com.hansalser.cloudservice.Common.utils.CloudServiceUtil;
import com.hansalser.cloudservice.Common.utils.MD5Util;
import com.hansalser.cloudservice.Common.utils.SortUtil;
import com.hansalser.cloudservice.account.dao.UserMapper;
import com.hansalser.cloudservice.account.entity.model.UserDO;
import com.hansalser.cloudservice.account.entity.model.UserRoleDO;
import com.hansalser.cloudservice.account.service.IUserRoleService;
import com.hansalser.cloudservice.account.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:02
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private ShiroRealm shiroRealm;

    @Override
    public UserDO findByName(String username) {
        return this.baseMapper.findByName(username);
    }

    @Override
    public IPage<UserDO> findUserDetail(UserDO user, QueryRequest request) {
        Page<UserDO> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "userId", CloudServiceConstant.ORDER_ASC, false);
        return this.baseMapper.findUserDetailPage(page, user);
    }

    @Override
    public UserDO findUserDetail(String username) {
        UserDO param = new UserDO();
        param.setUserName(username);
        List<UserDO> users = this.baseMapper.findUserDetail(param);
        return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
    }

    @Override
    @Transactional
    public void updateLoginTime(String username) {
        UserDO user = new UserDO();
        user.setLastLoginTime(new Date());
        this.baseMapper.update(user, new LambdaQueryWrapper<UserDO>().eq(UserDO::getUserName, username));
    }

    @Override
    @Transactional
    public void createUser(UserDO user) {
        user.setCreateAt(new Date());
        user.setStatus(UserDO.STATUS_VALID);
        user.setAvatar(UserDO.DEFAULT_AVATAR);
        user.setTheme(UserDO.THEME_BLACK);
        user.setIsTab(UserDO.TAB_OPEN);
        user.setPassword(MD5Util.encrypt(user.getUserName(), UserDO.DEFAULT_PASSWORD));
        save(user);
        // 保存用户角色
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
    }

    @Override
    @Transactional
    public void deleteUsers(String[] userIds) {
        List<String> list = Arrays.asList(userIds);
        // 删除用户
        this.removeByIds(list);
        // 删除关联角色
        this.userRoleService.deleteUserRolesByUserId(list);
    }

    @Override
    @Transactional
    public void updateUser(UserDO user) {
        // 更新用户
        user.setPassword(null);
        user.setUserName(null);
        user.setUpdateAt(new Date());
        updateById(user);
        // TODO：更新关联角色
        //this.userRoleService.remove(new LambdaQueryWrapper<UserRoleDO>().eq(UserRoleDO::getUserId, user.getUserId()));
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);

        UserDO currentUser = CloudServiceUtil.getCurrentUser();
        if (StringUtils.equalsIgnoreCase(currentUser.getUserName(), user.getUserName())) {
            shiroRealm.clearCache();
        }
    }

    @Override
    @Transactional
    public void resetPassword(String[] usernames) {
        Arrays.stream(usernames).forEach(username -> {
            UserDO user = new UserDO();
            user.setPassword(MD5Util.encrypt(username, UserDO.DEFAULT_PASSWORD));
            this.baseMapper.update(user, new LambdaQueryWrapper<UserDO>().eq(UserDO::getUserName, username));
        });
    }

    @Override
    @Transactional
    public void regist(String username, String password) {
        UserDO user = new UserDO();
        user.setPassword(MD5Util.encrypt(username, password));
        user.setUserName(username);
        user.setCreateAt(new Date());
        user.setStatus(UserDO.STATUS_VALID);
        user.setSex(UserDO.SEX_UNKNOW);
        user.setAvatar(UserDO.DEFAULT_AVATAR);
        user.setTheme(UserDO.THEME_BLACK);
        user.setIsTab(UserDO.TAB_OPEN);
        user.setDescription("注册用户");
        this.save(user);

        //TODO:注册用户给用户默认角色
//        UserRoleDO ur = new UserRoleDO();
//        ur.setUserId(user.getUserId());
//        ur.setRoleId(2L); // 注册用户角色 ID
//        this.userRoleService.save(ur);
    }

    @Override
    @Transactional
    public void updatePassword(String username, String password) {
        UserDO user = new UserDO();
        user.setPassword(MD5Util.encrypt(username, password));
        user.setUpdateAt(new Date());
        this.baseMapper.update(user, new LambdaQueryWrapper<UserDO>().eq(UserDO::getUserName, username));
    }

    @Override
    @Transactional
    public void updateAvatar(String username, String avatar) {
        UserDO user = new UserDO();
        user.setAvatar(avatar);
        user.setUpdateAt(new Date());
        this.baseMapper.update(user, new LambdaQueryWrapper<UserDO>().eq(UserDO::getUserName, username));
    }

    @Override
    @Transactional
    public void updateTheme(String username, String theme, int isTab) {
        UserDO user = new UserDO();
        user.setTheme(theme);
        user.setIsTab(isTab);
        user.setUpdateAt(new Date());
        this.baseMapper.update(user, new LambdaQueryWrapper<UserDO>().eq(UserDO::getUserName, username));
    }

    @Override
    @Transactional
    public void updateProfile(UserDO user) {
        user.setUserName(null);
        user.setRoleId(null);
        user.setPassword(null);
        updateById(user);
    }

    private void setUserRoles(UserDO user, String[] roles) {
        List<UserRoleDO> userRoles = new ArrayList<>();
        Arrays.stream(roles).forEach(roleId -> {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(user.getUserId());
            ur.setRoleId(Long.valueOf(roleId));
            userRoles.add(ur);
        });
        //TODO:批量绑定用户角色
        //userRoleService.saveBatch(userRoles);
    }
}
