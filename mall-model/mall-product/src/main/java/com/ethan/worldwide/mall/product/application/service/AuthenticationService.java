package com.ethan.worldwide.mall.product.application.service;

import com.ethan.worldwide.security.core.service.IAuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author zhenghui
 * @Description 认证用户服务类
 * @Date 2022/7/5
 */
public class AuthenticationService implements IAuthenticationService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
