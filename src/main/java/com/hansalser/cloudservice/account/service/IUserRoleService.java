package com.hansalser.cloudservice.account.service;

import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:03
 */
public interface IUserRoleService {
    /**
     * 通过角色 id 删除
     *
     * @param roleIds 角色 id
     */
    void deleteUserRolesByRoleId(List<String> roleIds);

    /**
     * 通过用户 id 删除
     *
     * @param userIds 用户 id
     */
    void deleteUserRolesByUserId(List<String> userIds);
}
