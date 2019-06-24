//package com.hansalser.cloudservice.account.controller;
//
//import com.hansalser.cloudservice.Common.constant.ResponseMessage;
//import com.hansalser.cloudservice.Common.entity.DeptTree;
//import com.hansalser.cloudservice.Common.exception.CloudServiceException;
//import com.hansalser.cloudservice.account.entity.model.DeptDO;
//import com.hansalser.cloudservice.account.service.IDeptService;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequestMapping("dept")
//public class DeptController {
//
//    private Logger logger = LoggerFactory.getLogger(DeptController.class);
//
//    @Autowired
//    private IDeptService deptService;
//
//    @RequestMapping("select/tree")
//    public List<DeptTree<DeptDO>> getDeptTree() throws CloudServiceException {
//        try {
//            return this.deptService.findDepts();
//        } catch (Exception e) {
//            String message = "获取部门树失败";
//            logger.error(message, e);
//            throw new CloudServiceException(message);
//        }
//    }
//
//    @RequestMapping("tree")
//    public ResponseMessage getDeptTree(DeptDO dept) throws CloudServiceException {
//        try {
//            List<DeptTree<DeptDO>> depts = this.deptService.findDepts(dept);
//            return new CloudServiceException().success().data(depts);
//        } catch (Exception e) {
//            String message = "获取部门树失败";
//            logger.error(message, e);
//            throw new CloudServiceException(message);
//        }
//    }
//
//    @Log("新增部门")
//    @PostMapping
//    @RequiresPermissions("dept:add")
//    public FebsResponse addDept(@Valid Dept dept) throws FebsException {
//        try {
//            this.deptService.createDept(dept);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "新增部门失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @Log("删除部门")
//    @GetMapping("delete/{deptIds}")
//    @RequiresPermissions("dept:delete")
//    public FebsResponse deleteDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) throws FebsException {
//        try {
//            String[] ids = deptIds.split(StringPool.COMMA);
//            this.deptService.deleteDepts(ids);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "删除部门失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @Log("修改部门")
//    @PostMapping("update")
//    @RequiresPermissions("dept:update")
//    public FebsResponse updateDept(@Valid Dept dept) throws FebsException {
//        try {
//            this.deptService.updateDept(dept);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改部门失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @GetMapping("excel")
//    @RequiresPermissions("dept:export")
//    public void export(Dept dept, QueryRequest request, HttpServletResponse response) throws FebsException {
//        try {
//            List<Dept> depts = this.deptService.findDepts(dept, request);
//            ExcelKit.$Export(Dept.class, response).downXlsx(depts, false);
//        } catch (Exception e) {
//            String message = "导出Excel失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//}
