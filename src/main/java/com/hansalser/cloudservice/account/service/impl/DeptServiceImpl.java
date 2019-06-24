package com.hansalser.cloudservice.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hansalser.cloudservice.Common.entity.CloudServiceConstant;
import com.hansalser.cloudservice.Common.entity.DeptTree;
import com.hansalser.cloudservice.Common.entity.QueryRequest;
import com.hansalser.cloudservice.Common.utils.SortUtil;
import com.hansalser.cloudservice.Common.utils.TreeUtil;
import com.hansalser.cloudservice.account.dao.DeptMapper;
import com.hansalser.cloudservice.account.entity.model.DeptDO;
import com.hansalser.cloudservice.account.service.IDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author chenglin.luo
 * @date 2019/6/23 17:04
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptDO> implements IDeptService {
    @Override
    public List<DeptTree<DeptDO>> findDepts() {
        List<DeptDO> depts = this.baseMapper.selectList(new QueryWrapper<>());
        List<DeptTree<DeptDO>> trees = this.convertDepts(depts);
        return TreeUtil.buildDeptTree(trees);
    }

    @Override
    public List<DeptTree<DeptDO>> findDepts(DeptDO dept) {
        QueryWrapper<DeptDO> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(dept.getDeptName()))
            queryWrapper.lambda().eq(DeptDO::getDeptName, dept.getDeptName());
        queryWrapper.lambda().orderByAsc(DeptDO::getDeptOrder);

        List<DeptDO> depts = this.baseMapper.selectList(queryWrapper);
        List<DeptTree<DeptDO>> trees =  this.convertDepts(depts);
        return TreeUtil.buildDeptTree(trees);
    }

    @Override
    public List<DeptDO> findDepts(DeptDO dept, QueryRequest request) {
        QueryWrapper<DeptDO> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(dept.getDeptName()))
            queryWrapper.lambda().eq(DeptDO::getDeptName, dept.getDeptName());
        SortUtil.handleWrapperSort(request, queryWrapper, "deptOrder", CloudServiceConstant.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createDept(DeptDO dept) {
        Long parentId = dept.getParentId();
        if (parentId == null)
            dept.setParentId(0L);
        dept.setCreateAt(new Date());
        this.save(dept);
    }

    @Override
    @Transactional
    public void updateDept(DeptDO dept) {
        dept.setUpdateAt(new Date());
        this.baseMapper.updateById(dept);
    }

    @Override
    @Transactional
    public void deleteDepts(String[] deptIds) {
        Arrays.stream(deptIds).forEach(deptId -> this.baseMapper.deleteDepts(deptId));
    }

    private List<DeptTree<DeptDO>> convertDepts(List<DeptDO> depts){
        List<DeptTree<DeptDO>> trees = new ArrayList<>();
        depts.forEach(dept -> {
            DeptTree<DeptDO> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getDeptId()));
            tree.setParentId(String.valueOf(dept.getParentId()));
            tree.setName(dept.getDeptName());
            tree.setData(dept);
            trees.add(tree);
        });
        return trees;
    }
}
