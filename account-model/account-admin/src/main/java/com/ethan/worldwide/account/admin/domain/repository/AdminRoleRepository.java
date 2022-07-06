package com.ethan.worldwide.account.admin.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ethan.worldwide.account.admin.domain.bo.role.ContentAdminRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.role.CreateAdminRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.role.QueryAdminRoleBo;
import com.ethan.worldwide.account.admin.infra.dao.po.role.AdminRolePo;
import com.ethan.worldwide.account.admin.domain.convert.AdminRolePoConvert;
import com.ethan.worldwide.account.admin.infra.dao.AdminRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Integer add(CreateAdminRoleBo createAdminRoleBo) {
        AdminRolePo adminRolePo = AdminRolePoConvert.INSTANCE.createBoToPo(createAdminRoleBo);
        adminRoleMapper.insert(adminRolePo);
        return adminRolePo.getId();
    }

    public ContentAdminRoleBo get(QueryAdminRoleBo queryAdminRoleBo) {
        LambdaQueryWrapper<AdminRolePo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (queryAdminRoleBo.getId()!=null) {
            lambdaQueryWrapper.eq(AdminRolePo::getId, queryAdminRoleBo.getId());
        }
        if (StrUtil.isNotEmpty(queryAdminRoleBo.getName())&& StrUtil.isNotBlank(queryAdminRoleBo.getName())) {
            lambdaQueryWrapper.eq(AdminRolePo::getName, queryAdminRoleBo.getName());
        }
        AdminRolePo adminRolePo = adminRoleMapper.selectOne(lambdaQueryWrapper);
        return AdminRolePoConvert.INSTANCE.toContentBo(adminRolePo);
    }

    public List<ContentAdminRoleBo> listByUserId(Integer userId) {
        return adminRoleMapper.listByUserId(userId);
    }

    private LambdaQueryWrapper<AdminRolePo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(AdminRolePo.class);
    }
}
