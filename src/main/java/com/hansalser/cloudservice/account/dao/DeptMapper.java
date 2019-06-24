package com.hansalser.cloudservice.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hansalser.cloudservice.account.entity.model.DeptDO;
import org.springframework.stereotype.Component;

/**
 * @author chenglin.luo
 * @date 2019/6/23 16:59
 */
@Component
public interface DeptMapper extends BaseMapper<DeptDO> {
    void deleteDepts(String deptId);
}
