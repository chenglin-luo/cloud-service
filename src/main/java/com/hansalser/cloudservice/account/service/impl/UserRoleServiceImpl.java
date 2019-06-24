package com.hansalser.cloudservice.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hansalser.cloudservice.account.dao.UserRoleMapper;
import com.hansalser.cloudservice.account.entity.model.UserRoleDO;
import com.hansalser.cloudservice.account.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:05
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleDO> implements IUserRoleService {

    @Override
    @Transactional
    public void deleteUserRolesByRoleId(List<String> roleIds) {
        this.baseMapper.delete(new QueryWrapper<UserRoleDO>().lambda().in(UserRoleDO::getRoleId, roleIds));
    }

    @Override
    @Transactional
    public void deleteUserRolesByUserId(List<String> userIds) {
        this.baseMapper.delete(new QueryWrapper<UserRoleDO>().lambda().in(UserRoleDO::getUserId, userIds));
    }
}
