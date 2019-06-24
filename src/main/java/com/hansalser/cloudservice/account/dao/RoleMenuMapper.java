package com.hansalser.cloudservice.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hansalser.cloudservice.account.entity.model.RoleMenuDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:00
 */
@Component
public interface RoleMenuMapper extends BaseMapper<RoleMenuDO> {
    /**
     * 递归删除菜单/按钮
     *
     * @param menuId menuId
     */
    void deleteRoleMenus(String menuId);
}
