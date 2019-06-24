//package com.hansalser.cloudservice.account.controller;
//
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.hansalser.cloudservice.Common.controller.BaseController;
//import com.hansalser.cloudservice.Common.entity.FebsResponse;
//import com.hansalser.cloudservice.Common.entity.QueryRequest;
//import com.hansalser.cloudservice.Common.utils.MD5Util;
//import com.hansalser.cloudservice.account.entity.model.UserDO;
//import com.hansalser.cloudservice.account.service.IUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
//import java.util.Map;
//
///**
// * @author chenglin.luo
// * @date 2019/6/22 19:22
// */
//@Slf4j
//@RestController
//@RequestMapping("user")
//public class UserController extends BaseController {
//
//    private Logger logger = LoggerFactory.getLogger(UserController.class);
//
//    @Autowired
//    private IUserService userService;
//
//    @RequestMapping("{username}")
//    public UserDO getUser(@NotBlank(message = "{required}") @PathVariable String username) {
//        return this.userService.findUserDetail(username);
//    }
//
//    @RequestMapping("check/{username}")
//    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String username, String userId) {
//        return this.userService.findByName(username) == null || StringUtils.isNotBlank(userId);
//    }
//
//    @RequestMapping("list")
//    @RequiresPermissions("user:view")
//    public FebsResponse userList(UserDO user, QueryRequest request) {
//        Map<String, Object> dataTable = getDataTable(this.userService.findUserDetail(user, request));
//        return new FebsResponse().success().data(dataTable);
//    }
//
//    @PostMapping
//    @RequiresPermissions("user:add")
//    public FebsResponse addUser(@Valid UserDO user) throws FebsException {
//        try {
//            this.userService.createUser(user);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "新增用户失败";
//            logger.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @GetMapping("delete/{userIds}")
//    @RequiresPermissions("user:delete")
//    public FebsResponse deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws FebsException {
//        try {
//            String[] ids = userIds.split(StringPool.COMMA);
//            this.userService.deleteUsers(ids);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "删除用户失败";
//            logger.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @PostMapping("update")
//    @RequiresPermissions("user:update")
//    public FebsResponse updateUser(@Valid UserDO user) throws FebsException {
//        try {
//            if (user.getUserId() == null)
//                throw new FebsException("用户ID为空");
//            this.userService.updateUser(user);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改用户失败";
//            logger.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @RequestMapping("password/reset/{usernames}")
//    @RequiresPermissions("user:password:reset")
//    public FebsResponse resetPassword(@NotBlank(message = "{required}") @PathVariable String usernames) throws FebsException {
//        try {
//            String[] usernameArr = usernames.split(StringPool.COMMA);
//            this.userService.resetPassword(usernameArr);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "重置用户密码失败";
//            logger.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @PostMapping("password/update")
//    public FebsResponse updatePassword(
//            @NotBlank(message = "{required}") String oldPassword,
//            @NotBlank(message = "{required}") String newPassword) throws FebsException {
//        try {
//            UserDO user = getCurrentUser();
//            if (!StringUtils.equals(user.getPassword(), MD5Util.encrypt(user.getUsername(), oldPassword))) {
//                throw new FebsException("原密码不正确");
//            }
//            userService.updatePassword(user.getUserName(), newPassword);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改密码失败，" + e.getMessage();
//            log.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @GetMapping("avatar/{image}")
//    public FebsResponse updateAvatar(@NotBlank(message = "{required}") @PathVariable String image) throws FebsException {
//        try {
//            UserDO user = getCurrentUser();
//            this.userService.updateAvatar(user.getUserName(), image);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改头像失败";
//            logger.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @PostMapping("theme/update")
//    public FebsResponse updateTheme(String theme, String isTab) throws FebsException {
//        try {
//            UserDO user = getCurrentUser();
//            this.userService.updateTheme(user.getUserName(), theme, isTab);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改系统配置失败";
//            logger.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    @PostMapping("profile/update")
//    public FebsResponse updateProfile(UserDO user) throws FebsException {
//        try {
//            UserDO currentUser = getCurrentUser();
//            user.setUserId(currentUser.getUserId());
//            this.userService.updateProfile(user);
//            return new FebsResponse().success();
//        } catch (Exception e) {
//            String message = "修改个人信息失败";
//            logger.error(message, e);
//            throw new FebsException(message);
//        }
//    }
//
//    //TODO:导出excel
////    @GetMapping("excel")
////    @RequiresPermissions("user:export")
////    public void export(QueryRequest queryRequest, User user, HttpServletResponse response) throws FebsException {
////        try {
////            List<User> users = this.userService.findUserDetail(user, queryRequest).getRecords();
////            ExcelKit.$Export(User.class, response).downXlsx(users, false);
////        } catch (Exception e) {
////            String message = "导出Excel失败";
////            log.error(message, e);
////            throw new FebsException(message);
////        }
////    }
//}
