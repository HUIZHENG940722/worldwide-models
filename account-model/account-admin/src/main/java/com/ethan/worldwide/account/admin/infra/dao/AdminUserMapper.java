package com.ethan.worldwide.account.admin.infra.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.worldwide.account.admin.infra.dao.po.user.AdminUserPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zhenghui
 * @Description 后台管理员后台访问接口
 * @Date 2022/6/27
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUserPo> {
}
