package com.hansalser.cloudservice.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hansalser.cloudservice.account.entity.UserDTO;
import com.hansalser.cloudservice.account.entity.model.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 16:59
 */
@Component
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    UserDO findByName(String username);

    /**
     * 查找用户详细信息
     *
     * @param page 分页对象
     * @param user 用户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<UserDO> findUserDetailPage(Page page, @Param("user") UserDO user);

    /**
     * 查找用户详细信息
     *
     * @param user 用户对象，用于传递查询条件
     * @return List<User>
     */
    List<UserDO> findUserDetail(@Param("user") UserDO user);
}
