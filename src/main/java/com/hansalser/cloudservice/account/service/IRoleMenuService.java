package com.hansalser.cloudservice.account.service;

import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:03
 */
public interface IRoleMenuService {
    /**
     * 通过角色 id 删除
     *
     * @param roleIds 角色 id
     */
    void deleteRoleMenusByRoleId(List<String> roleIds);

    /**
     * 通过菜单（按钮）id 删除
     *
     * @param menuIds 菜单（按钮）id
     */
    void deleteRoleMenusByMenuId(List<String> menuIds);

    /**
     * 递归删除菜单/按钮
     *
     * @param menuId menuId
     */
    void deleteRoleMenus(String menuId);
}
