package com.shuaicai.expression;

import com.shuaicai.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName SCExpressionRoot
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/7 23:10
 * @PackagePath com.shuaicai.expression
 * @Version 1.0
 */
@Component("EX")
public class SCExpressionRoot {

    public boolean hasAuthority(String authority) {
        //获取当前对象的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        //判断用户权限集合中是否存在
        return permissions.contains(authority);
    }
}
