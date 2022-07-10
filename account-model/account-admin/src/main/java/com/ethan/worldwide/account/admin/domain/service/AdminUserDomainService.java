package com.ethan.worldwide.account.admin.domain.service;

import cn.hutool.crypto.digest.BCrypt;
import com.ethan.worldwide.account.admin.domain.bo.user.AdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.QueryAdminUserBo;
import com.ethan.worldwide.account.admin.domain.repository.AdminUserRepository;
import com.ethan.worldwide.account.admin.infra.exception.AccountAdminServiceException;
import com.ethan.worldwide.common.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @Author zhenghui
 * @Description 系统管理员领域服务
 * @Date 2022/6/26
 */
@Service
public class AdminUserDomainService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AppConfig appConfig;

    /**
     * 初始化系统管理员
     */
    @PostConstruct
    public void initSysAdmin() {
        // 1 校验
        // 2 业务
        // 2.1 验证系统管理员是否已存在
        QueryAdminUserBo queryAdminUserBo = new QueryAdminUserBo();
        queryAdminUserBo.setUsername(appConfig.getUsername());
        AdminUserBo byName = adminUserRepository.get(queryAdminUserBo);
        // 2.2 执行插入逻辑
        if (byName == null) {
            AdminUserBo adminUserBo = new AdminUserBo();
            adminUserBo.setUsername(appConfig.getUsername());
            adminUserBo.setPassword(BCrypt.hashpw(appConfig.getPassword()));
            adminUserBo.setStatus(1);
            adminUserBo.setCreateTime(new Date(System.nanoTime()));
            Integer add = adminUserRepository.add(adminUserBo);
        }
    }

    /**
     * 创建后台管理用户
     *
     * @param adminUserBo
     * @return
     */
    public Integer create(AdminUserBo adminUserBo) {
        // 1 核心校验
        // 1.1 验证用户名是否重复
        QueryAdminUserBo queryAdminUserBo = new QueryAdminUserBo();
        queryAdminUserBo.setUsername(adminUserBo.getUsername());
        AdminUserBo byName = adminUserRepository.get(queryAdminUserBo);
        if (byName != null) {
            AccountAdminServiceException.assertException(HttpStatus.CONFLICT, "后台用户名称重复");
        }
        // 2 核心业务
        // 2.1 密码加密
        String hashpw = BCrypt.hashpw(adminUserBo.getPassword());
        adminUserBo.setPassword(hashpw);
        // 2.2 插入数据
        return adminUserRepository.add(adminUserBo);
        // 3 返回结果
    }

    /**
     * 获取用户内容啊
     *
     * @param queryAdminUserBo
     * @return
     */
    public AdminUserBo get(QueryAdminUserBo queryAdminUserBo) {
        // 1 核心校验
        // 2 核心业务
        return adminUserRepository.get(queryAdminUserBo);
        // 3 返回结果
    }
}
