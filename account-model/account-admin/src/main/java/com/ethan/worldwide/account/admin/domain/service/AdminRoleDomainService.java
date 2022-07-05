package com.ethan.worldwide.account.admin.domain.service;

import com.ethan.worldwide.account.admin.domain.bo.role.ContentAdminRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.role.CreateAdminRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.role.QueryAdminRoleBo;
import com.ethan.worldwide.account.admin.domain.repository.AdminRoleRepository;
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
public class AdminRoleDomainService {

    @Autowired
    private AdminRoleRepository adminRoleRepository;

    /**
     * 创建后台用户角色
     *
     * @param createAdminRoleBo
     * @return
     */
    @Transactional
    public Integer create(CreateAdminRoleBo createAdminRoleBo) {
        // 1 核心校验
        // 1.1 角色名称是否重复
        QueryAdminRoleBo queryAdminRoleBo = new QueryAdminRoleBo();
        queryAdminRoleBo.setName(createAdminRoleBo.getName());
        ContentAdminRoleBo byName = adminRoleRepository.get(queryAdminRoleBo);
        if (byName != null) {
            AccountAdminServiceException.assertException(HttpStatus.CONFLICT, "角色名称重复");
        }
        // 2 核心业务
        return adminRoleRepository.add(createAdminRoleBo);
        // 3 返回结果
    }

    /**
     * 获取后台用户角色内容
     *
     * @param queryAdminRoleBo
     * @return
     */
    public ContentAdminRoleBo get(QueryAdminRoleBo queryAdminRoleBo) {
        // 1 核心校验
        // 2 核心业务
        return adminRoleRepository.get(queryAdminRoleBo);
        // 3 返回结果
    }

    /**
     * 根据用户编码获取角色列表
     *
     * @param userId
     * @return
     */
    public List<ContentAdminRoleBo> listByUserId(Integer userId) {
        // 1 核心校验
        // 2 核心业务
        return adminRoleRepository.listByUserId(userId);
        // 3 返回结果
    }
}
