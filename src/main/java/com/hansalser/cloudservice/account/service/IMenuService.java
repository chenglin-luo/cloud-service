package com.hansalser.cloudservice.account.service;

import com.hansalser.cloudservice.Common.entity.MenuTree;
import com.hansalser.cloudservice.account.entity.model.MenuDO;

import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:03
 */
public interface IMenuService {
    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<MenuDO> findUserPermissions(String username);

    /**
     * 查找用户菜单集合
     *
     * @param username 用户名
     * @return 用户菜单集合
     */
    MenuTree<MenuDO> findUserMenus(String username);

    /**
     * 查找所有的菜单/按钮 （树形结构）
     *
     * @return MenuTree<Menu>
     */
    MenuTree<MenuDO> findMenus(MenuDO menu);

    /**
     * 查找所有的菜单/按钮
     *
     * @return MenuTree<Menu>
     */
    List<MenuDO> findMenuList(MenuDO menu);

    /**
     * 新增菜单（按钮）
     *
     * @param menu 菜单（按钮）对象
     */
    void createMenu(MenuDO menu);

    /**
     * 修改菜单（按钮）
     *
     * @param menu 菜单（按钮）对象
     */
    void updateMenu(MenuDO menu);

    /**
     * 删除菜单（按钮）
     *
     * @param menuIds 菜单（按钮）id
     */
    void deleteMeuns(String menuIds);
}
