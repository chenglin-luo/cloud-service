package com.hansalser.cloudservice.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hansalser.cloudservice.Common.authentication.ShiroRealm;
import com.hansalser.cloudservice.Common.entity.CloudServiceConstant;
import com.hansalser.cloudservice.Common.entity.QueryRequest;
import com.hansalser.cloudservice.Common.utils.SortUtil;
import com.hansalser.cloudservice.account.dao.RoleMapper;
import com.hansalser.cloudservice.account.entity.model.RoleDO;
import com.hansalser.cloudservice.account.entity.model.RoleMenuDO;
import com.hansalser.cloudservice.account.service.IRoleMenuService;
import com.hansalser.cloudservice.account.service.IRoleService;
import com.hansalser.cloudservice.account.service.IUserRoleService;
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
 * @date 2019/6/23 17:04
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements IRoleService {

    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private ShiroRealm shiroRealm;

    @Override
    public List<RoleDO> findUserRole(String username) {

        return this.baseMapper.findUserRole(username);
    }

    @Override
    public List<RoleDO> findRoles(RoleDO role) {
        QueryWrapper<RoleDO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(role.getRoleName()))
            queryWrapper.lambda().like(RoleDO::getRoleName, role.getRoleName());
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<RoleDO> findRoles(RoleDO role, QueryRequest request) {
        Page<RoleDO> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "createAt", CloudServiceConstant.ORDER_DESC, false);
        return this.baseMapper.findRolePage(page, role);
    }

    @Override
    public RoleDO findByName(String roleName) {
        return this.baseMapper.selectOne(new QueryWrapper<RoleDO>().lambda().eq(RoleDO::getRoleName, roleName));
    }

    @Override
    @Transactional
    public void createRole(RoleDO role) {
        role.setCreateAt(new Date());
        this.baseMapper.insert(role);
        this.saveRoleMenus(role);
    }

    @Override
    @Transactional
    public void updateRole(RoleDO role) {
        role.setUpdateAt(new Date());
        this.updateById(role);
        List<String> roleIdList = new ArrayList<>();
        roleIdList.add(String.valueOf(role.getRoleId()));
        this.roleMenuService.deleteRoleMenusByRoleId(roleIdList);
        saveRoleMenus(role);

        shiroRealm.clearCache();
    }

    @Override
    @Transactional
    public void deleteRoles(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(StringPool.COMMA));
        this.baseMapper.delete(new QueryWrapper<RoleDO>().lambda().in(RoleDO::getRoleId, list));

        this.roleMenuService.deleteRoleMenusByRoleId(list);
        this.userRoleService.deleteUserRolesByRoleId(list);
    }

    private void saveRoleMenus(RoleDO role) {
        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = role.getMenuIds().split(StringPool.COMMA);
            List<RoleMenuDO> roleMenus = new ArrayList<>();
            Arrays.stream(menuIds).forEach(menuId -> {
                RoleMenuDO roleMenu = new RoleMenuDO();
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenu.setRoleId(role.getRoleId());
                roleMenus.add(roleMenu);
            });
            //TODO:批量保存角色菜单
            //roleMenuService.saveBatch(roleMenus);
        }
    }
}
