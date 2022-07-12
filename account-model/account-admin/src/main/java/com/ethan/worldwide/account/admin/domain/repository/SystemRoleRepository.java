package com.ethan.worldwide.account.admin.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ethan.worldwide.account.admin.domain.bo.role.SystemRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.role.QuerySystemRoleBo;
import com.ethan.worldwide.account.admin.domain.convert.SystemRolePoConvert;
import com.ethan.worldwide.account.admin.infra.dao.po.role.SystemRolePo;
import com.ethan.worldwide.account.admin.infra.dao.SystemRoleMapper;
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
public class SystemRoleRepository {

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    public Integer add(SystemRoleBo systemRoleBo) {
        SystemRolePo systemRolePo = SystemRolePoConvert.INSTANCE.toPo(systemRoleBo);
        try {

        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "插入后台角色异常");
        }
        systemRoleMapper.insert(systemRolePo);
        return systemRolePo.getId();
    }

    public SystemRoleBo get(QuerySystemRoleBo querySystemRoleBo) {
        LambdaQueryWrapper<SystemRolePo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (querySystemRoleBo.getId() != null) {
            lambdaQueryWrapper.eq(SystemRolePo::getId, querySystemRoleBo.getId());
        }
        if (StrUtil.isNotEmpty(querySystemRoleBo.getName()) && StrUtil.isNotBlank(querySystemRoleBo.getName())) {
            lambdaQueryWrapper.eq(SystemRolePo::getName, querySystemRoleBo.getName());
        }
        SystemRolePo systemRolePo = null;
        try {
            systemRolePo = systemRoleMapper.selectOne(lambdaQueryWrapper);
        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "获取后台角色异常");
        }
        return SystemRolePoConvert.INSTANCE.toBo(systemRolePo);
    }

    public List<SystemRoleBo> listByUserId(Integer userId) {
        List<SystemRolePo> systemRolePoList = null;
        try {
            systemRolePoList = systemRoleMapper.listByUserId(userId);
        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "获取后台角色列表异常");
        }
        return SystemRolePoConvert.INSTANCE.toBo(systemRolePoList);
    }

    private LambdaQueryWrapper<SystemRolePo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(SystemRolePo.class);
    }
}
