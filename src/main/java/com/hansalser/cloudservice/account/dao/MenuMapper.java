package com.hansalser.cloudservice.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hansalser.cloudservice.account.entity.model.MenuDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 16:59
 */
@Component
public interface MenuMapper extends BaseMapper<MenuDO> {
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
    List<MenuDO> findUserMenus(String username);

    /**
     * 递归删除菜单/按钮
     *
     * @param menuId menuId
     */
    void deleteMenus(String menuId);
}
