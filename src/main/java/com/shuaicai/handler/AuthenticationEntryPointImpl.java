package com.shuaicai.handler;

import com.alibaba.fastjson.JSON;
import com.shuaicai.domain.ResponseResult;
import com.shuaicai.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AuthenticationEntryPointImpl
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/7 17:17
 * @PackagePath com.shuaicai.handler
 * @Version 1.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //处理异常
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败，请查询登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
