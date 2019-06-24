//package com.hansalser.cloudservice.account.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author: chenglin.luo
// * @create: 2019-06-24 16:15
// **/
//@Slf4j
//@RestController
//@RequestMapping("role")
//public class RoleController {
//
//    @Autowired
//    private IRoleService roleService;
//
//    @GetMapping
//    public FebsResponse getAllRoles(Role role) {
//        return new FebsResponse().success().data(roleService.findRoles(role));
//    }
//
//    @GetMapping("list")
//    @RequiresPermissions("role:view")
//    public FebsResponse roleList(Role role, QueryRequest request) {
//        Map<String, Object> dataTable = getDataTable(this.roleService.findRoles(role, request));
//        return new FebsResponse().success().data(dataTable);
//    }
//
//    @Log("新增角色")
//    @PostMapping
//    @RequiresPermissions("role:add")
//    public FebsResponse addRole(@Valid Role role) throws FebsException {
//        try {
//            this.roleService.createRole(role);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "新增角色失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @Log("删除角色")
//    @GetMapping("delete/{roleIds}")
//    @RequiresPermissions("role:delete")
//    public FebsResponse deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) throws FebsException {
//        try {
//            this.roleService.deleteRoles(roleIds);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "删除角色失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @Log("修改角色")
//    @PostMapping("update")
//    @RequiresPermissions("role:update")
//    public FebsResponse updateRole(Role role) throws FebsException {
//        try {
//            this.roleService.updateRole(role);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改角色失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @GetMapping("excel")
//    @RequiresPermissions("role:export")
//    public void export(QueryRequest queryRequest, Role role, HttpServletResponse response) throws FebsException {
//        try {
//            List<Role> roles = this.roleService.findRoles(role, queryRequest).getRecords();
//            ExcelKit.$Export(Role.class, response).downXlsx(roles, false);
//        } catch (Exception e) {
//            String message = "导出Excel失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//}
