package com.ethan.worldwide.security.core.handler;

import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zhenghui
 * @Description 未认证处理器
 * @Date 2022/6/25
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ServletUtil.write(response, String.valueOf(HttpStatus.UNAUTHORIZED), MediaType.APPLICATION_JSON_VALUE);
    }
}
