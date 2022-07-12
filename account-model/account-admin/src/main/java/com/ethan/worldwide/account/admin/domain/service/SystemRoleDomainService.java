package com.ethan.worldwide.account.admin.domain.service;

import com.ethan.worldwide.account.admin.domain.bo.role.SystemRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.role.QuerySystemRoleBo;
import com.ethan.worldwide.account.admin.domain.repository.SystemRoleRepository;
import com.ethan.worldwide.account.admin.infra.exception.AccountAdminServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zhenghui
 * @Description 后台用户角色领域服务
 * @Date 2022/6/30
 */
@Service
public class SystemRoleDomainService {

    @Autowired
    private SystemRoleRepository systemRoleRepository;

    /**
     * 创建后台用户角色
     *
     * @param systemRoleBo
     * @return
     */
    @Transactional
    public Integer create(SystemRoleBo systemRoleBo) {
        // 1 核心校验
        // 1.1 角色名称是否重复
        QuerySystemRoleBo querySystemRoleBo = new QuerySystemRoleBo();
        querySystemRoleBo.setName(systemRoleBo.getName());
        SystemRoleBo byName = systemRoleRepository.get(querySystemRoleBo);
        if (byName != null) {
            AccountAdminServiceException.assertException(HttpStatus.CONFLICT, "角色名称重复");
        }
        // 2 核心业务
        return systemRoleRepository.add(systemRoleBo);
        // 3 返回结果
    }

    /**
     * 获取后台用户角色内容
     *
     * @param querySystemRoleBo
     * @return
     */
    public SystemRoleBo get(QuerySystemRoleBo querySystemRoleBo) {
        // 1 核心校验
        // 2 核心业务
        return systemRoleRepository.get(querySystemRoleBo);
        // 3 返回结果
    }

    /**
     * 根据用户编码获取角色列表
     *
     * @param userId
     * @return
     */
    public List<SystemRoleBo> listByUserId(Integer userId) {
        // 1 核心校验
        // 2 核心业务
        return systemRoleRepository.listByUserId(userId);
        // 3 返回结果
    }
}
