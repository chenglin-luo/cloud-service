package com.hansalser.cloudservice.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hansalser.cloudservice.account.entity.RoleDTO;
import com.hansalser.cloudservice.account.entity.model.RoleDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 16:59
 */
@Component
public interface RoleMapper extends BaseMapper<RoleDO> {
    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 用户角色集合
     */
    List<RoleDO> findUserRole(String username);

    /**
     * 查找角色详情
     *
     * @param page 分页
     * @param role 角色
     * @return IPage<User>
     */
    IPage<RoleDO> findRolePage(Page page, @Param("role") RoleDO role);
}
