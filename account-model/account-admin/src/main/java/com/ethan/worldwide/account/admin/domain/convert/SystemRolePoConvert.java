package com.ethan.worldwide.account.admin.domain.convert;

import com.ethan.worldwide.account.admin.domain.bo.role.SystemRoleBo;
import com.ethan.worldwide.account.admin.infra.dao.po.role.SystemRolePo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author zhenghui
 * @Description 后台用户角色PO转换器
 * @Date 2022/6/30
 */
@Mapper
public interface SystemRolePoConvert {

    SystemRolePoConvert INSTANCE = Mappers.getMapper(SystemRolePoConvert.class);

    SystemRoleBo toBo(SystemRolePo systemRolePo);

    SystemRolePo toPo(SystemRoleBo systemRoleBo);

    List<SystemRoleBo> toBo(List<SystemRolePo> systemRolePoList);
}
