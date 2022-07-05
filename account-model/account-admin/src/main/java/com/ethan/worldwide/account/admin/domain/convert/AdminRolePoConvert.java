package com.ethan.worldwide.account.admin.domain.convert;

import com.ethan.worldwide.account.admin.domain.bo.role.ContentAdminRoleBo;
import com.ethan.worldwide.account.admin.domain.bo.role.CreateAdminRoleBo;
import com.ethan.worldwide.account.admin.infra.dao.po.role.AdminRolePo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Author zhenghui
 * @Description 后台用户角色PO转换器
 * @Date 2022/6/30
 */
@Mapper
public interface AdminRolePoConvert {

    AdminRolePoConvert INSTANCE = Mappers.getMapper(AdminRolePoConvert.class);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createTime", expression = "java(new java.util.Date(System.currentTimeMillis()))"),
        @Mapping(target = "updateTime", ignore = true)
    })
    AdminRolePo createBoToPo(CreateAdminRoleBo createAdminRoleBo);

    ContentAdminRoleBo toContentBo(AdminRolePo adminRolePo);
}
