package com.ethan.worldwide.account.admin.application.service;

import cn.hutool.crypto.digest.BCrypt;
import com.ethan.worldwide.account.admin.domain.bo.role.SystemRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.user.SystemUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.LoginSystemUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.QuerySystemUserBo;
import com.ethan.worldwide.account.admin.domain.service.SystemRoleDomainService;
import com.ethan.worldwide.account.admin.domain.service.SystemUserDomainService;
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
public class SystemUserService implements IAuthenticationService {

    @Autowired
    private SystemUserDomainService systemUserDomainService;

    @Autowired
    private SystemRoleDomainService systemRoleDomainService;

    @Autowired
    private AppConfig appConfig;

    @Override
    public AuthenticationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1 校验
        // 2 业务
        // 2.1 获取用户基本信息
        QuerySystemUserBo querySystemUserBo = new QuerySystemUserBo();
        querySystemUserBo.setUsername(username);
        SystemUserBo byName = systemUserDomainService.get(querySystemUserBo);
        AuthenticationUser authenticationUser = new AuthenticationUser();
        authenticationUser.setUsername(byName.getUsername());
        authenticationUser.setPassword(byName.getPassword());
        authenticationUser.setIsEnable(byName.getStatus().equals(1));
        // 2.2 获取用户的角色信息
        List<SystemRoleBo> contentSystemRoleBos = systemRoleDomainService.listByUserId(byName.getId());
        List<String> collect = contentSystemRoleBos.stream().map(SystemRoleBo::getName).collect(Collectors.toList());
        authenticationUser.setRoles(collect);
        // 3 返回结果信息
        return authenticationUser;
    }

    public Integer sysAdminAddAdminUser(Integer sysAdminUserId, SystemUserBo systemUserBo) {
        // 1 校验
        // 1.1 验证是否是系统管理员
        QuerySystemUserBo queryById = new QuerySystemUserBo();
        queryById.setId(sysAdminUserId);
        SystemUserBo byId = systemUserDomainService.get(queryById);
        if (!appConfig.getUsername().equals(byId.getUsername())) {
            AccountAdminServiceException.assertException(HttpStatus.CONFLICT, "只有系统管理员可以创建后台用户");
        }
        // 2 业务
        return systemUserDomainService.create(systemUserBo);
        // 3 返回结果
    }

    public String login(LoginSystemUserBo loginSystemUserBo) {
        // 1 校验
        // 2 业务
        // 2.1 获取认证用户信息
        UserDetails userDetails = loadUserByUsername(loginSystemUserBo.getUsername());
        // 2.1.1 验证用户是否存在
        if (userDetails == null) {
            AccountAdminServiceException.assertException(HttpStatus.NOT_FOUND, "用户不存在");
        }
        // 2.1.2 验证密码是否正确
        if (!BCrypt.checkpw(loginSystemUserBo.getPassword(), userDetails.getPassword())) {
            AccountAdminServiceException.assertException(HttpStatus.CONFLICT, "用户密码错误");
        }
        // 2.2 生成token信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return JwtTokenUtils.generateToken(userDetails);
        // 3 返回结果
    }
}
