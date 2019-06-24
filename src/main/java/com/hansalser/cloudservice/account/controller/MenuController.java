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
//@RequestMapping("menu")
//public class MenuController {
//
//    @Autowired
//    private IMenuService menuService;
//
//    @GetMapping("{username}")
//    public FebsResponse getUserMenus(@NotBlank(message = "{required}") @PathVariable String username) throws FebsException {
//        User currentUser = getCurrentUser();
//        if (!StringUtils.equalsIgnoreCase(username, currentUser.getUsername()))
//            throw new FebsException("您无权获取别人的菜单");
//        MenuTree<Menu> userMenus = this.menuService.findUserMenus(username);
//        return new FebsResponse().data(userMenus);
//    }
//
//    @GetMapping("tree")
//    public FebsResponse getMenuTree(Menu menu) throws FebsException {
//        try {
//            MenuTree<Menu> menus = this.menuService.findMenus(menu);
//            return new FebsResponse().success().data(menus.getChilds());
//        } catch (Exception e) {
//            String message = "获取菜单树失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @Log("新增菜单/按钮")
//    @PostMapping
//    @RequiresPermissions("menu:add")
//    public FebsResponse addMenu(@Valid Menu menu) throws FebsException {
//        try {
//            this.menuService.createMenu(menu);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "新增菜单/按钮失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @Log("删除菜单/按钮")
//    @GetMapping("delete/{menuIds}")
//    @RequiresPermissions("menu:delete")
//    public FebsResponse deleteMenus(@NotBlank(message = "{required}") @PathVariable String menuIds) throws FebsException {
//        try {
//            this.menuService.deleteMeuns(menuIds);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "删除菜单/按钮失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @Log("修改菜单/按钮")
//    @PostMapping("update")
//    @RequiresPermissions("menu:update")
//    public FebsResponse updateMenu(@Valid Menu menu) throws FebsException {
//        try {
//            this.menuService.updateMenu(menu);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改菜单/按钮失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @GetMapping("excel")
//    @RequiresPermissions("menu:export")
//    public void export(Menu menu, HttpServletResponse response) throws FebsException {
//        try {
//            List<Menu> menus = this.menuService.findMenuList(menu);
//            ExcelKit.$Export(Menu.class, response).downXlsx(menus, false);
//        } catch (Exception e) {
//            String message = "导出Excel失败";
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//}
