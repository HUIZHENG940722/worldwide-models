package com.ethan.worldwide.account.admin.domain.convert;

import com.ethan.worldwide.account.admin.domain.bo.role.AdminRoleBo;
import com.ethan.worldwide.account.admin.infra.dao.po.role.AdminRolePo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author zhenghui
 * @Description 后台用户角色PO转换器
 * @Date 2022/6/30
 */
@Mapper
public interface AdminRolePoConvert {

    AdminRolePoConvert INSTANCE = Mappers.getMapper(AdminRolePoConvert.class);

    AdminRoleBo toBo(AdminRolePo adminRolePo);

    AdminRolePo toPo(AdminRoleBo adminRoleBo);

    List<AdminRoleBo> toBo(List<AdminRolePo> adminRolePoList);
}
