package com.ethan.worldwide.account.admin.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ethan.worldwide.account.admin.domain.bo.user.SystemUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.QuerySystemUserBo;
import com.ethan.worldwide.account.admin.domain.convert.SystemUserPoConvert;
import com.ethan.worldwide.account.admin.infra.dao.po.user.SystemUserPo;
import com.ethan.worldwide.account.admin.infra.dao.SystemUserMapper;
import com.ethan.worldwide.account.admin.infra.exception.AccountAdminServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

/**
 * @Author zhenghui
 * @Description 系统管理员数据仓库
 * @Date 2022/6/26
 */
@Repository
public class SystemUserRepository {

    @Autowired
    private SystemUserMapper systemUserMapper;

    public SystemUserBo get(QuerySystemUserBo querySystemUserBo) {
        LambdaQueryWrapper<SystemUserPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (StrUtil.isNotEmpty(querySystemUserBo.getUsername()) && StrUtil.isNotBlank(querySystemUserBo.getUsername().trim())) {
            lambdaQueryWrapper.eq(SystemUserPo::getUsername, querySystemUserBo.getUsername());
        }
        if (querySystemUserBo.getId() != null) {
            lambdaQueryWrapper.eq(SystemUserPo::getId, querySystemUserBo.getId());
        }
        SystemUserPo systemUserPo = null;
        try {
            systemUserPo = systemUserMapper.selectOne(lambdaQueryWrapper);
        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "后台用户查询异常");
        }
        return SystemUserPoConvert.INSTANCE.toBo(systemUserPo);
    }

    public Integer add(SystemUserBo systemUserBo) {
        SystemUserPo boToPo = SystemUserPoConvert.INSTANCE.toPo(systemUserBo);
        try {
            systemUserMapper.insert(boToPo);
        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "后台用户插入异常");
        }
        return boToPo.getId();
    }

    private LambdaQueryWrapper<SystemUserPo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(SystemUserPo.class);
    }
}
