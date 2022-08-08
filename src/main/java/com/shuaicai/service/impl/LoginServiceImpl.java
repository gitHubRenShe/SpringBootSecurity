package com.shuaicai.service.impl;

import com.shuaicai.domain.LoginUser;
import com.shuaicai.domain.ResponseResult;
import com.shuaicai.domain.User;
import com.shuaicai.service.LoginService;
import com.shuaicai.utils.JwtUtil;
import com.shuaicai.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/3 22:43
 * @PackagePath com.shuaicai.service.impl
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager authentication进行用户验证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //如果认证没通过，给出相关提示
        if (Objects.isNull(authenticate)){
            new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userid生成一个jwt 存入ResponseResult返回
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的信息存入redis userid作为key
        redisCache.setCacheObject("login:"+id,loginUser);

        return new ResponseResult(200,"登录成功",map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();

        redisCache.deleteObject("login:"+id);
        return new ResponseResult(200,"注销成功");
    }
}
