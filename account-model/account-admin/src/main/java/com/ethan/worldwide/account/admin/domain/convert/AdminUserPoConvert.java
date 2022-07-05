package com.ethan.worldwide.account.admin.domain.convert;

import com.ethan.worldwide.account.admin.domain.bo.user.ContentAdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.CreateAdminUserBo;
import com.ethan.worldwide.account.admin.infra.dao.po.user.AdminUserPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Author zhenghui
 * @Description 系统管理员PO转换器
 * @Date 2022/6/26
 */
@Mapper
public interface AdminUserPoConvert {
    AdminUserPoConvert INSTANCE = Mappers.getMapper(AdminUserPoConvert.class);

    ContentAdminUserBo toContentBo(AdminUserPo selectOne);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "deleted", expression = "java(1)"),
        @Mapping(target = "loginTime", ignore = true),
        @Mapping(target = "createTime", expression = "java(new java.util.Date(System.currentTimeMillis()))"),
        @Mapping(target = "updateTime", ignore = true)
    })
    AdminUserPo createBoToPo(CreateAdminUserBo createAdminUserBo);
}
