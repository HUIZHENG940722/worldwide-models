package com.ethan.worldwide.account.admin.inter.controller;

import com.ethan.worldwide.account.admin.application.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenghui
 * @Description 后台用户角色用户接口
 * @Date 2022/6/30
 */
@RestController
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;


}
