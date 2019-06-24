package com.hansalser.cloudservice.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hansalser.cloudservice.Common.authentication.ShiroRealm;
import com.hansalser.cloudservice.Common.entity.MenuTree;
import com.hansalser.cloudservice.Common.utils.TreeUtil;
import com.hansalser.cloudservice.account.dao.MenuMapper;
import com.hansalser.cloudservice.account.dao.RoleMenuMapper;
import com.hansalser.cloudservice.account.entity.model.MenuDO;
import com.hansalser.cloudservice.account.service.IMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:04
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuDO> implements IMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private ShiroRealm shiroRealm;

    @Override
    public List<MenuDO> findUserPermissions(String username) {
        return this.baseMapper.findUserPermissions(username);
    }

    @Override
    public MenuTree<MenuDO> findUserMenus(String username) {
        List<MenuDO> menus = this.baseMapper.findUserMenus(username);
        List<MenuTree<MenuDO>> trees = this.convertMenus(menus);
        return TreeUtil.buildMenuTree(trees);
    }

    @Override
    public MenuTree<MenuDO> findMenus(MenuDO menu) {
        QueryWrapper<MenuDO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(menu.getMenuName())) {
            queryWrapper.lambda().like(MenuDO::getMenuName, menu.getMenuName());
        }
        queryWrapper.lambda().orderByAsc(MenuDO::getMenuOrder);
        List<MenuDO> menus = this.baseMapper.selectList(queryWrapper);
        List<MenuTree<MenuDO>> trees = this.convertMenus(menus);

        return TreeUtil.buildMenuTree(trees);
    }

    @Override
    public List<MenuDO> findMenuList(MenuDO menu) {
        QueryWrapper<MenuDO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(menu.getMenuName())) {
            queryWrapper.lambda().like(MenuDO::getMenuName, menu.getMenuName());
        }
        queryWrapper.lambda().orderByAsc(MenuDO::getMenuId).orderByAsc(MenuDO::getMenuOrder);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createMenu(MenuDO menu) {
        menu.setCreateAt(new Date());
        this.setMenu(menu);
        this.baseMapper.insert(menu);
    }


    @Override
    @Transactional
    public void updateMenu(MenuDO menu) {
        menu.setUpdateAt(new Date());
        this.setMenu(menu);
        this.baseMapper.updateById(menu);

        shiroRealm.clearCache();
    }

    @Override
    @Transactional
    public void deleteMeuns(String menuIds) {
        String[] menuIdsArray = menuIds.split(StringPool.COMMA);
        for (String menuId : menuIdsArray) {
            // 递归删除这些菜单/按钮
            this.baseMapper.deleteMenus(menuId);
            this.roleMenuMapper.deleteRoleMenus(menuId);
        }

        shiroRealm.clearCache();
    }

    private List<MenuTree<MenuDO>> convertMenus(List<MenuDO> menus) {
        List<MenuTree<MenuDO>> trees = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree<MenuDO> tree = new MenuTree<>();
            tree.setId(String.valueOf(menu.getMenuId()));
            tree.setParentId(String.valueOf(menu.getParentId()));
            tree.setTitle(menu.getMenuName());
            tree.setIcon(menu.getIcon());
            tree.setHref(menu.getMenuUrl());
            tree.setData(menu);
            trees.add(tree);
        });
        return trees;
    }

    private void setMenu(MenuDO menu) {
        if (menu.getParentId() == null)
            menu.setParentId(MenuDO.TOP_NODE);
        if (MenuDO.TYPE_BUTTON.equals(menu.getMenuType())) {
            menu.setMenuUrl(null);
            menu.setIcon(null);
        }
    }
}
