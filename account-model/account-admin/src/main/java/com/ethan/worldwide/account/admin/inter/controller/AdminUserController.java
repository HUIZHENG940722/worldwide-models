package com.ethan.worldwide.account.admin.inter.controller;

import cn.hutool.core.util.StrUtil;
import com.ethan.worldwide.account.admin.application.service.AdminUserService;
import com.ethan.worldwide.account.admin.domain.bo.user.CreateAdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.LoginAdminUserBo;
import com.ethan.worldwide.account.admin.infra.exception.AccountAdminServiceException;
import com.ethan.worldwide.account.admin.inter.assembler.AdminUserDtoConvert;
import com.ethan.worldwide.common.bo.AuthenticationUser;
import com.ethan.worldwide.openapi.interfaces.api.AccountAdminApi;
import com.ethan.worldwide.openapi.interfaces.api.dto.AddAdminReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.LoginAdminReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenghui
 * @Description 系统管理员用户接口层
 * @Date 2022/6/26
 */
@RestController
public class AdminUserController implements AccountAdminApi {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public ResponseEntity<String> login(LoginAdminReq loginAdminReq) {
        // 1 数据转换
        LoginAdminUserBo loginAdminUserBo = AdminUserDtoConvert.INSTANCE.toBo(loginAdminReq);
        // 2 业务
        String accessToken = adminUserService.login(loginAdminUserBo);
        // 3 返回结果
        if (StrUtil.isBlank(accessToken)) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "登录获取token失败");
        }
        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> sysAdminAddAdminUser(Integer sysAdminUserId, AddAdminReq addAdminReq) {
        // 1 数据转换
        CreateAdminUserBo createAdminUserBo = AdminUserDtoConvert.INSTANCE.toBo(addAdminReq);
        // 2 业务
        Integer adminUserId = adminUserService.sysAdminAddAdminUser(sysAdminUserId, createAdminUserBo);
        // 3 返回结果
        if (adminUserId == null) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "创建后台用户失败");
        }
        return new ResponseEntity<>(adminUserId, HttpStatus.CREATED);
    }

    /**
     * 获取认证用户
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/loadUserByUsername")
    public AuthenticationUser loadUserByUsername(String username) {
        return adminUserService.loadUserByUsername(username);
    }
}
