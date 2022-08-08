package com.shuaicai.controller;

import com.shuaicai.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/3 19:53
 * @PackagePath com.shuaicai.controller
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello2")
    @PreAuthorize("hasAnyAuthority('system:dept:list1')")
    public String helloController2(@RequestParam("name") String name) {

        return "你好:" + name + " !";
    }

    @GetMapping("/hello")
    @PreAuthorize("@EX.hasAuthority('system:dept:list')")
    public String helloController() {

        return "你好: !";
    }

    @RequestMapping("/testCors")
    public ResponseResult testCors() {

        return new ResponseResult(200,"testCors");
    }

    @GetMapping("/test01")
    public ResponseResult test01() {

        return new ResponseResult(200,"test01");
    }
}
