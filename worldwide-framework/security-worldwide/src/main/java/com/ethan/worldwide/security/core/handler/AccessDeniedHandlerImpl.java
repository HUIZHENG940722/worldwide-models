package com.ethan.worldwide.security.core.handler;

import cn.hutool.extra.servlet.ServletUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author zhenghui
 * @Description 无权限处理器
 * @Date 2022/6/25
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ServletUtil.write(response, String.valueOf(HttpStatus.FORBIDDEN), MediaType.APPLICATION_JSON_VALUE);
    }
}
