package com.ethan.worldwide.account.admin.domain.service;

import cn.hutool.crypto.digest.BCrypt;
import com.ethan.worldwide.account.admin.domain.bo.user.ContentAdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.CreateAdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.QueryAdminUserBo;
import com.ethan.worldwide.account.admin.domain.repository.AdminUserRepository;
import com.ethan.worldwide.account.admin.infra.exception.AccountAdminServiceException;
import com.ethan.worldwide.common.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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
        QueryAdminUserBo adminUserBo = new QueryAdminUserBo();
        adminUserBo.setUsername(appConfig.getUsername());
        ContentAdminUserBo contentAdminUserBo = adminUserRepository.get(adminUserBo);
        // 2.2 执行插入逻辑
        if (contentAdminUserBo == null) {
            CreateAdminUserBo createAdminUserBo = new CreateAdminUserBo();
            createAdminUserBo.setUsername(appConfig.getUsername());
            createAdminUserBo.setPassword(BCrypt.hashpw(appConfig.getPassword()));
            createAdminUserBo.setStatus(1);
            Integer add = adminUserRepository.add(createAdminUserBo);
        }
    }

    /**
     * 创建后台管理用户
     *
     * @param createAdminUserBo
     * @return
     */
    public Integer create(CreateAdminUserBo createAdminUserBo) {
        // 1 核心校验
        // 1.1 验证用户名是否重复
        QueryAdminUserBo queryAdminUserBo = new QueryAdminUserBo();
        queryAdminUserBo.setUsername(createAdminUserBo.getUsername());
        ContentAdminUserBo contentAdminUserBo = adminUserRepository.get(queryAdminUserBo);
        if (contentAdminUserBo != null) {
            AccountAdminServiceException.assertException(HttpStatus.CONFLICT, "后台用户名称重复");
        }
        // 2 核心业务
        // 2.1 密码加密
        String hashpw = BCrypt.hashpw(createAdminUserBo.getPassword());
        createAdminUserBo.setPassword(hashpw);
        // 2.2 插入数据
        return adminUserRepository.add(createAdminUserBo);
        // 3 返回结果
    }

    /**
     * 获取用户内容啊
     *
     * @param queryAdminUserBo
     * @return
     */
    public ContentAdminUserBo get(QueryAdminUserBo queryAdminUserBo) {
        // 1 核心校验
        // 2 核心业务
        return adminUserRepository.get(queryAdminUserBo);
        // 3 返回结果
    }
}
