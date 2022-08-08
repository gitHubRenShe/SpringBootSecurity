package com.shuaicai.controller;

import com.shuaicai.domain.ResponseResult;
import com.shuaicai.domain.User;
import com.shuaicai.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/3 22:32
 * @PackagePath com.shuaicai.controller
 * @Version 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    //登录
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }
    //退出
    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
