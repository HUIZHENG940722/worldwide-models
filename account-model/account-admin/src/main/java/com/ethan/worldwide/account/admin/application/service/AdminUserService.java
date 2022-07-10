package com.ethan.worldwide.account.admin.application.service;

import cn.hutool.crypto.digest.BCrypt;
import com.ethan.worldwide.account.admin.domain.bo.role.AdminRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.user.AdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.LoginAdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.QueryAdminUserBo;
import com.ethan.worldwide.account.admin.domain.service.AdminRoleDomainService;
import com.ethan.worldwide.account.admin.domain.service.AdminUserDomainService;
import com.ethan.worldwide.account.admin.infra.exception.AccountAdminServiceException;
import com.ethan.worldwide.common.bo.AuthenticationUser;
import com.ethan.worldwide.common.config.AppConfig;
import com.ethan.worldwide.security.core.service.IAuthenticationService;
import com.ethan.worldwide.security.core.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhenghui
 * @Description 系统管理员应用服务
 * @Date 2022/6/26
 */
@Service
public class AdminUserService implements IAuthenticationService {

    @Autowired
    private AdminUserDomainService adminUserDomainService;

    @Autowired
    private AdminRoleDomainService adminRoleDomainService;

    @Autowired
    private AppConfig appConfig;

    @Override
    public AuthenticationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1 校验
        // 2 业务
        // 2.1 获取用户基本信息
        QueryAdminUserBo queryAdminUserBo = new QueryAdminUserBo();
        queryAdminUserBo.setUsername(username);
        AdminUserBo byName = adminUserDomainService.get(queryAdminUserBo);
        AuthenticationUser authenticationUser = new AuthenticationUser();
        authenticationUser.setUsername(byName.getUsername());
        authenticationUser.setPassword(byName.getPassword());
        authenticationUser.setIsEnable(byName.getStatus().equals(1));
        // 2.2 获取用户的角色信息
        List<AdminRoleBo> contentAdminRoleBos = adminRoleDomainService.listByUserId(byName.getId());
        List<String> collect = contentAdminRoleBos.stream().map(AdminRoleBo::getName).collect(Collectors.toList());
        authenticationUser.setRoles(collect);
        // 3 返回结果信息
        return authenticationUser;
    }

    public Integer sysAdminAddAdminUser(Integer sysAdminUserId, AdminUserBo adminUserBo) {
        // 1 校验
        // 1.1 验证是否是系统管理员
        QueryAdminUserBo queryById = new QueryAdminUserBo();
        queryById.setId(sysAdminUserId);
        AdminUserBo byId = adminUserDomainService.get(queryById);
        if (!appConfig.getUsername().equals(byId.getUsername())) {
            AccountAdminServiceException.assertException(HttpStatus.CONFLICT, "只有系统管理员可以创建后台用户");
        }
        // 2 业务
        return adminUserDomainService.create(adminUserBo);
        // 3 返回结果
    }

    public String login(LoginAdminUserBo loginAdminUserBo) {
        // 1 校验
        // 2 业务
        // 2.1 获取认证用户信息
        UserDetails userDetails = loadUserByUsername(loginAdminUserBo.getUsername());
        // 2.1.1 验证用户是否存在
        if (userDetails == null) {
            AccountAdminServiceException.assertException(HttpStatus.NOT_FOUND, "用户不存在");
        }
        // 2.1.2 验证密码是否正确
        if (!BCrypt.checkpw(loginAdminUserBo.getPassword(), userDetails.getPassword())) {
            AccountAdminServiceException.assertException(HttpStatus.CONFLICT, "用户密码错误");
        }
        // 2.2 生成token信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return JwtTokenUtils.generateToken(userDetails);
        // 3 返回结果
    }
}
