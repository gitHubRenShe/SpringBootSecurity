package com.shuaicai.service;

import com.shuaicai.domain.ResponseResult;
import com.shuaicai.domain.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/3 22:42
 * @PackagePath com.shuaicai.service
 * @Version 1.0
 */
@Service
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
