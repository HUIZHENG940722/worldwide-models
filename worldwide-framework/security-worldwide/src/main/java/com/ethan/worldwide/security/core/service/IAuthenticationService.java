package com.ethan.worldwide.security.core.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @Author zhenghui
 * @Description 认证服务接口
 * @Date 2022/6/25
 */
@Service
public interface IAuthenticationService extends UserDetailsService {
}
