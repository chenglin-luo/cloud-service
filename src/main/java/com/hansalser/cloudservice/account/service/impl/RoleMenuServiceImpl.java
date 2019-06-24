package com.hansalser.cloudservice.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hansalser.cloudservice.account.dao.RoleMenuMapper;
import com.hansalser.cloudservice.account.entity.model.RoleMenuDO;
import com.hansalser.cloudservice.account.service.IRoleMenuService;
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
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuDO> implements IRoleMenuService {
    @Override
    @Transactional
    public void deleteRoleMenusByRoleId(List<String> roleIds) {
        this.baseMapper.delete(new QueryWrapper<RoleMenuDO>().lambda().in(RoleMenuDO::getRoleId, roleIds));
    }

    @Override
    @Transactional
    public void deleteRoleMenusByMenuId(List<String> menuIds) {
        this.baseMapper.delete(new QueryWrapper<RoleMenuDO>().lambda().in(RoleMenuDO::getRoleId, menuIds));
    }

    @Override
    @Transactional
    public void deleteRoleMenus(String menuId) {
        this.baseMapper.deleteRoleMenus(menuId);
    }
}
