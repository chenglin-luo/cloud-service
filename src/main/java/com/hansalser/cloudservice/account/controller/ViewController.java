package com.hansalser.cloudservice.account.controller;

import com.hansalser.cloudservice.Common.authentication.ShiroHelper;
import com.hansalser.cloudservice.Common.controller.BaseController;
import com.hansalser.cloudservice.Common.entity.CloudServiceConstant;
import com.hansalser.cloudservice.Common.utils.CloudServiceUtil;
import com.hansalser.cloudservice.Common.utils.DateUtil;
import com.hansalser.cloudservice.account.entity.model.UserDO;
import com.hansalser.cloudservice.account.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: chenglin.luo
 * @create: 2019-06-24 16:15
 **/
@Slf4j
@RestController
@RequestMapping("systemView")
public class ViewController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ShiroHelper shiroHelper;

    @GetMapping(value = "login")
    public Object login(HttpServletRequest request) {
        if (CloudServiceUtil.isAjaxRequest(request)) {
            throw new ExpiredSessionException();
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName(CloudServiceUtil.view("login"));
            return mav;
        }
    }

    @GetMapping("unauthorized")
    public String unauthorized() {
        return CloudServiceUtil.view("error/403");
    }



    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String index(Model model) {
        AuthorizationInfo authorizationInfo = shiroHelper.getCurrentuserAuthorizationInfo();
        UserDO user = super.getCurrentUser();
        user.setPassword("It's a secret");
        model.addAttribute("user", userService.findByName(user.getUserName())); // 获取实时的用户信息
        model.addAttribute("permissions", authorizationInfo.getStringPermissions());
        model.addAttribute("roles",authorizationInfo.getRoles());
        return "index";
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "layout")
    public String layout() {
        return CloudServiceUtil.view("layout");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "password/update")
    public String passwordUpdate() {
        return CloudServiceUtil.view("system/user/passwordUpdate");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "user/profile")
    public String userProfile() {
        return CloudServiceUtil.view("system/user/userProfile");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "user/avatar")
    public String userAvatar() {
        return CloudServiceUtil.view("system/user/avatar");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "user/profile/update")
    public String profileUpdate() {
        return CloudServiceUtil.view("system/user/profileUpdate");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "system/user")
    @RequiresPermissions("user:view")
    public String systemUser() {
        return CloudServiceUtil.view("system/user/user");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "system/user/add")
    @RequiresPermissions("user:add")
    public String systemUserAdd() {
        return CloudServiceUtil.view("system/user/userAdd");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "system/user/detail/{username}")
    @RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        resolveUserModel(username, model, true);
        return CloudServiceUtil.view("system/user/userDetail");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "system/user/update/{username}")
    @RequiresPermissions("user:update")
    public String systemUserUpdate(@PathVariable String username, Model model) {
        resolveUserModel(username, model, false);
        return CloudServiceUtil.view("system/user/userUpdate");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "system/role")
    @RequiresPermissions("role:view")
    public String systemRole() {
        return CloudServiceUtil.view("system/role/role");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return CloudServiceUtil.view("system/menu/menu");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "system/dept")
    @RequiresPermissions("dept:view")
    public String systemDept() {
        return CloudServiceUtil.view("system/dept/dept");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "index")
    public String pageIndex() {
        return CloudServiceUtil.view("index");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "404")
    public String error404() {
        return CloudServiceUtil.view("error/404");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "403")
    public String error403() {
        return CloudServiceUtil.view("error/403");
    }

    @GetMapping(CloudServiceConstant.VIEW_PREFIX + "500")
    public String error500() {
        return CloudServiceUtil.view("error/500");
    }

    private void resolveUserModel(String username, Model model, Boolean transform) {
        UserDO user = userService.findByName(username);
        model.addAttribute("user", user);
        if (transform) {
//            Integer ssex = user.getSex();
//            if (UserDO.SEX_MALE.equals(ssex)) user.setSex("男");
//            else if (UserDO.SEX_FEMALE.equals(ssex)) user.setSex("女");
//            else user.setSex("保密");
            Integer ssex = user.getSex();
            if (UserDO.SEX_MALE.equals(ssex)) user.setSex(0);
            else if (UserDO.SEX_FEMALE.equals(ssex)) user.setSex(1);
            else user.setSex(2);
        }
        if (user.getLastLoginTime() != null)
            model.addAttribute("lastLoginTime", DateUtil.getDateFormat(user.getLastLoginTime(), DateUtil.FULL_TIME_SPLIT_PATTERN));
    }
}
