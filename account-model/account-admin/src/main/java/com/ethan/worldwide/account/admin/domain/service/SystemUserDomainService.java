package com.ethan.worldwide.account.admin.domain.service;

import cn.hutool.crypto.digest.BCrypt;
import com.ethan.worldwide.account.admin.domain.bo.user.SystemUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.QuerySystemUserBo;
import com.ethan.worldwide.account.admin.domain.repository.SystemUserRepository;
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
public class SystemUserDomainService {

    @Autowired
    private SystemUserRepository systemUserRepository;

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
        QuerySystemUserBo querySystemUserBo = new QuerySystemUserBo();
        querySystemUserBo.setUsername(appConfig.getUsername());
        SystemUserBo byName = systemUserRepository.get(querySystemUserBo);
        // 2.2 执行插入逻辑
        if (byName == null) {
            SystemUserBo systemUserBo = new SystemUserBo();
            systemUserBo.setUsername(appConfig.getUsername());
            systemUserBo.setPassword(BCrypt.hashpw(appConfig.getPassword()));
            systemUserBo.setStatus(1);
            systemUserBo.setCreateTime(new Date(System.nanoTime()));
            Integer add = systemUserRepository.add(systemUserBo);
        }
    }

    /**
     * 创建后台管理用户
     *
     * @param systemUserBo
     * @return
     */
    public Integer create(SystemUserBo systemUserBo) {
        // 1 核心校验
        // 1.1 验证用户名是否重复
        QuerySystemUserBo querySystemUserBo = new QuerySystemUserBo();
        querySystemUserBo.setUsername(systemUserBo.getUsername());
        SystemUserBo byName = systemUserRepository.get(querySystemUserBo);
        if (byName != null) {
            AccountAdminServiceException.assertException(HttpStatus.CONFLICT, "后台用户名称重复");
        }
        // 2 核心业务
        // 2.1 密码加密
        String hashpw = BCrypt.hashpw(systemUserBo.getPassword());
        systemUserBo.setPassword(hashpw);
        // 2.2 插入数据
        return systemUserRepository.add(systemUserBo);
        // 3 返回结果
    }

    /**
     * 获取用户内容啊
     *
     * @param querySystemUserBo
     * @return
     */
    public SystemUserBo get(QuerySystemUserBo querySystemUserBo) {
        // 1 核心校验
        // 2 核心业务
        return systemUserRepository.get(querySystemUserBo);
        // 3 返回结果
    }
}
