package com.ethan.worldwide.mall.product.application.service;

import com.ethan.worldwide.common.bo.AuthenticationUser;
import com.ethan.worldwide.mall.product.application.service.feign.IAdminUserService;
import com.ethan.worldwide.security.core.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author zhenghui
 * @Description 认证用户服务类
 * @Date 2022/7/5
 */
@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IAdminUserService adminUserService;

    @Override
    public AuthenticationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminUserService.loadUserByUsername(username);
    }
}
