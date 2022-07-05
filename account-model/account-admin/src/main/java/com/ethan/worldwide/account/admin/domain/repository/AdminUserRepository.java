package com.ethan.worldwide.account.admin.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ethan.worldwide.account.admin.domain.bo.user.ContentAdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.CreateAdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.QueryAdminUserBo;
import com.ethan.worldwide.account.admin.domain.convert.AdminUserPoConvert;
import com.ethan.worldwide.account.admin.infra.dao.AdminUserMapper;
import com.ethan.worldwide.account.admin.infra.dao.po.user.AdminUserPo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ContentAdminUserBo get(QueryAdminUserBo queryAdminUserBo) {
        LambdaQueryWrapper<AdminUserPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (StrUtil.isNotEmpty(queryAdminUserBo.getUsername()) && StrUtil.isNotBlank(queryAdminUserBo.getUsername().trim())) {
            lambdaQueryWrapper.eq(AdminUserPo::getUsername, queryAdminUserBo.getUsername());
        }
        if (queryAdminUserBo.getId() != null) {
            lambdaQueryWrapper.eq(AdminUserPo::getId, queryAdminUserBo.getId());
        }
        return AdminUserPoConvert.INSTANCE.toContentBo(adminUserMapper.selectOne(lambdaQueryWrapper));
    }

    public Integer add(CreateAdminUserBo createAdminUserBo) {
        AdminUserPo boToPo = AdminUserPoConvert.INSTANCE.createBoToPo(createAdminUserBo);
        adminUserMapper.insert(boToPo);
        return boToPo.getId();
    }

    private LambdaQueryWrapper<AdminUserPo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(AdminUserPo.class);
    }
}
