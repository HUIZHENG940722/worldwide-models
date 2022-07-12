package com.ethan.worldwide.account.admin.inter.controller;

import cn.hutool.core.util.StrUtil;
import com.ethan.worldwide.account.admin.application.service.SystemUserService;
import com.ethan.worldwide.account.admin.domain.bo.user.LoginSystemUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.SystemUserBo;
import com.ethan.worldwide.account.admin.infra.exception.AccountAdminServiceException;
import com.ethan.worldwide.account.admin.inter.assembler.SystemUserDtoConvert;
import com.ethan.worldwide.common.bo.AuthenticationUser;
import com.ethan.worldwide.openapi.interfaces.api.AccountAdminApi;
import com.ethan.worldwide.openapi.interfaces.api.dto.AddAdminReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.LoginAdminReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhenghui
 * @Description 系统管理员用户接口层
 * @Date 2022/6/26
 */
@RestController
public class SystemUserController implements AccountAdminApi {

    @Autowired
    private SystemUserService systemUserService;

    @Override
    public ResponseEntity<String> login(LoginAdminReq loginAdminReq) {
        // 1 数据转换
        LoginSystemUserBo loginSystemUserBo = SystemUserDtoConvert.INSTANCE.toBo(loginAdminReq);
        // 2 业务
        String accessToken = systemUserService.login(loginSystemUserBo);
        // 3 返回结果
        if (StrUtil.isBlank(accessToken)) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "登录获取token失败");
        }
        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> sysAdminAddAdminUser(Integer sysAdminUserId, AddAdminReq addAdminReq) {
        // 1 数据转换
        SystemUserBo systemUserBo = SystemUserDtoConvert.INSTANCE.toBo(addAdminReq);
        // 2 业务
        Integer adminUserId = systemUserService.sysAdminAddAdminUser(sysAdminUserId, systemUserBo);
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
    @GetMapping(value = "/loadUserByUsername")
    public AuthenticationUser loadUserByUsername(@RequestParam(value = "username") String username) {
        return systemUserService.loadUserByUsername(username);
    }
}
