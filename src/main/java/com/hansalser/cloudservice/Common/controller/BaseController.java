package com.hansalser.cloudservice.Common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hansalser.cloudservice.account.entity.model.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chenglin.luo
 * @create: 2019-06-24 16:38
 **/
public class BaseController {

    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected UserDO getCurrentUser() {
        return (UserDO) getSubject().getPrincipal();
    }

    protected Session getSession() {
        return getSubject().getSession();
    }

    protected Session getSession(Boolean flag) {
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("rows", pageInfo.getRecords());
        data.put("total", pageInfo.getTotal());
        return data;
    }

}
