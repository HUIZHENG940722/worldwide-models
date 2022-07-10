package com.ethan.worldwide.account.admin.infra.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.worldwide.account.admin.infra.dao.po.role.AdminRolePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zhenghui
 * @Description 后台用户角色数据访问接口
 * @Date 2022/6/30
 */
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRolePo> {
    List<AdminRolePo> listByUserId(@Param("userId") Integer userId);
}
