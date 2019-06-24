package com.hansalser.cloudservice.account.service;

import com.hansalser.cloudservice.Common.entity.DeptTree;
import com.hansalser.cloudservice.Common.entity.QueryRequest;
import com.hansalser.cloudservice.account.entity.DeptDTO;
import com.hansalser.cloudservice.account.entity.model.DeptDO;

import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:03
 */
public interface IDeptService {
    /**
     * 获取部门树（下拉选使用）
     *
     * @return 部门树集合
     */
    List<DeptTree<DeptDO>> findDepts();

    /**
     * 获取部门列表（树形列表）
     *
     * @param dept 部门对象（传递查询参数）
     * @return 部门树
     */
    List<DeptTree<DeptDO>> findDepts(DeptDO dept);

    /**
     * 获取部门树（供Excel导出）
     *
     * @param dept    部门对象（传递查询参数）
     * @param request QueryRequest
     * @return List<Dept>
     */
    List<DeptDO> findDepts(DeptDO dept, QueryRequest request);

    /**
     * 新增部门
     *
     * @param dept 部门对象
     */
    void createDept(DeptDO dept);

    /**
     * 修改部门
     *
     * @param dept 部门对象
     */
    void updateDept(DeptDO dept);

    /**
     * 删除部门
     *
     * @param deptIds 部门 ID集合
     */
    void deleteDepts(String[] deptIds);
}
