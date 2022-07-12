package com.ethan.worldwide.account.admin.application.service;

import com.ethan.worldwide.account.admin.domain.service.SystemRoleDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhenghui
 * @Description 后台用户角色应用服务
 * @Date 2022/6/30
 */
@Service
public class SystemRoleService {

    @Autowired
    private SystemRoleDomainService systemRoleDomainService;

}
