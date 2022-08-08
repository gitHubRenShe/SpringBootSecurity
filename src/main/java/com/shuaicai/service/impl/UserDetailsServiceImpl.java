package com.shuaicai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.shuaicai.domain.LoginUser;
import com.shuaicai.domain.User;
import com.shuaicai.mapper.MenuMapper;
import com.shuaicai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/3 21:03
 * @PackagePath com.shuaicai.service.impl
 * @Version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);

        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }

        //TODO 查询对应权限
//        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        //把权限封装成UserDetails返回
        return new LoginUser(user,list);
    }
}
