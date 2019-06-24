package com.hansalser.cloudservice.account.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hansalser.cloudservice.Common.entity.QueryRequest;
import com.hansalser.cloudservice.account.entity.model.RoleDO;

import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:02
 */
public interface IRoleService {
    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 用户角色集合
     */
    List<RoleDO> findUserRole(String username);

    /**
     * 查找所有角色
     *
     * @param role 角色对象（用于传递查询条件）
     * @return 角色集合
     */
    List<RoleDO> findRoles(RoleDO role);

    /**
     * 查找所有角色（分页）
     *
     * @param role    角色对象（用于传递查询条件）
     * @param request request
     * @return IPage
     */
    IPage<RoleDO> findRoles(RoleDO role, QueryRequest request);

    /**
     * 通过角色名称查找相应角色
     *
     * @param roleName 角色名称
     * @return 角色
     */
    RoleDO findByName(String roleName);

    /**
     * 新增角色
     *
     * @param role 待新增的角色
     */
    void createRole(RoleDO role);

    /**
     * 修改角色
     *
     * @param role 待修改的角色
     */
    void updateRole(RoleDO role);


    /**
     * 删除角色
     *
     * @param roleIds 待删除角色的 id
     */
    void deleteRoles(String roleIds);
}
