package com.shuaicai.filter;

import com.shuaicai.domain.LoginUser;
import com.shuaicai.utils.JwtUtil;
import com.shuaicai.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Objects;

/**
 * @ClassName JwtAuthenticationTokenFilter
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/4 20:40
 * @PackagePath com.shuaicai.filter
 * @Version 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        String userId;
        //解析token
        try {
            Claims claims = JwtUtil.parseJWT(token);
            //获取userId
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法！");
        }
        //从redis中获取用户信息
        String redisKey = "login:"+ userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)){
            throw new RemoteException("用户未登录");
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到authenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
