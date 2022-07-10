package com.ethan.worldwide.account.admin.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ethan.worldwide.account.admin.domain.bo.user.AdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.QueryAdminUserBo;
import com.ethan.worldwide.account.admin.infra.dao.po.user.AdminUserPo;
import com.ethan.worldwide.account.admin.domain.convert.AdminUserPoConvert;
import com.ethan.worldwide.account.admin.infra.dao.AdminUserMapper;
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
public class AdminUserRepository {

    @Autowired
    private AdminUserMapper adminUserMapper;

    public AdminUserBo get(QueryAdminUserBo queryAdminUserBo) {
        LambdaQueryWrapper<AdminUserPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (StrUtil.isNotEmpty(queryAdminUserBo.getUsername()) && StrUtil.isNotBlank(queryAdminUserBo.getUsername().trim())) {
            lambdaQueryWrapper.eq(AdminUserPo::getUsername, queryAdminUserBo.getUsername());
        }
        if (queryAdminUserBo.getId() != null) {
            lambdaQueryWrapper.eq(AdminUserPo::getId, queryAdminUserBo.getId());
        }
        AdminUserPo adminUserPo = null;
        try {
            adminUserPo = adminUserMapper.selectOne(lambdaQueryWrapper);
        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "后台用户查询异常");
        }
        return AdminUserPoConvert.INSTANCE.toBo(adminUserPo);
    }

    public Integer add(AdminUserBo adminUserBo) {
        AdminUserPo boToPo = AdminUserPoConvert.INSTANCE.toPo(adminUserBo);
        try {
            adminUserMapper.insert(boToPo);
        } catch (AccountAdminServiceException e) {
            AccountAdminServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "后台用户插入异常");
        }
        return boToPo.getId();
    }

    private LambdaQueryWrapper<AdminUserPo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(AdminUserPo.class);
    }
}
