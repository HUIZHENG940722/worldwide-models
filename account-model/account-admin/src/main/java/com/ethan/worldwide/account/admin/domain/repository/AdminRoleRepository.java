package com.ethan.worldwide.account.admin.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ethan.worldwide.account.admin.domain.bo.role.AdminRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.role.QueryAdminRoleBo;
import com.ethan.worldwide.account.admin.infra.dao.po.role.AdminRolePo;
import com.ethan.worldwide.account.admin.domain.convert.AdminRolePoConvert;
import com.ethan.worldwide.account.admin.infra.dao.AdminRoleMapper;
import com.ethan.worldwide.account.admin.infra.exception.AccountAdminServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhenghui
 * @Description 后台用户角色数据仓库
 * @Date 2022/6/30
 */
@Repository
public class AdminRoleRepository {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    public Integer add(AdminRoleBo adminRoleBo) {
        AdminRolePo adminRolePo = AdminRolePoConvert.INSTANCE.toPo(adminRoleBo);
        try {

        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "插入后台角色异常");
        }
        adminRoleMapper.insert(adminRolePo);
        return adminRolePo.getId();
    }

    public AdminRoleBo get(QueryAdminRoleBo queryAdminRoleBo) {
        LambdaQueryWrapper<AdminRolePo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (queryAdminRoleBo.getId() != null) {
            lambdaQueryWrapper.eq(AdminRolePo::getId, queryAdminRoleBo.getId());
        }
        if (StrUtil.isNotEmpty(queryAdminRoleBo.getName()) && StrUtil.isNotBlank(queryAdminRoleBo.getName())) {
            lambdaQueryWrapper.eq(AdminRolePo::getName, queryAdminRoleBo.getName());
        }
        AdminRolePo adminRolePo = null;
        try {
            adminRolePo = adminRoleMapper.selectOne(lambdaQueryWrapper);
        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "获取后台角色异常");
        }
        return AdminRolePoConvert.INSTANCE.toBo(adminRolePo);
    }

    public List<AdminRoleBo> listByUserId(Integer userId) {
        List<AdminRolePo> adminRolePoList = null;
        try {
            adminRolePoList = adminRoleMapper.listByUserId(userId);
        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "获取后台角色列表异常");
        }
        return AdminRolePoConvert.INSTANCE.toBo(adminRolePoList);
    }

    private LambdaQueryWrapper<AdminRolePo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(AdminRolePo.class);
    }
}
