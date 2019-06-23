package com.hansalser.cloudservice.account.controller;

import com.google.gson.Gson;
import com.hansalser.cloudservice.Common.ResponseMessage;
import com.hansalser.cloudservice.account.controller.form.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author chenglin.luo
 * @date 2019/6/22 19:22
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private Gson gson;

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping(value = "test")
    public ResponseMessage test() {
        String str = "bbbbb";
        logger.info("aaaaaaaaaa ={}", str);
        System.out.println("------------test------------");
        UserForm userForm = new UserForm();
        userForm.setUserId(123L);
        userForm.setUserName("lcl");
        userForm.setPassword("123");
        logger.info("return data={}", gson.toJson(userForm));
        return ResponseMessage.success(userForm);
    }

    @RequestMapping(value = "string")
    public String string() {
        System.out.println("------------string------------");
        return "String";
    }
}
