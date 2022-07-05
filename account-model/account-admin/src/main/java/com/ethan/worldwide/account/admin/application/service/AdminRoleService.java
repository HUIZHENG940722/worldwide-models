package com.ethan.worldwide.account.admin.application.service;

import com.ethan.worldwide.account.admin.domain.service.AdminRoleDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhenghui
 * @Description 后台用户角色应用服务
 * @Date 2022/6/30
 */
@Service
public class AdminRoleService {

    @Autowired
    private AdminRoleDomainService adminRoleDomainService;

}
