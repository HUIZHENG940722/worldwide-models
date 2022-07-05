package com.ethan.worldwide.account.admin.inter.assembler;

import com.ethan.worldwide.account.admin.domain.bo.user.CreateAdminUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.LoginAdminUserBo;
import com.ethan.worldwide.openapi.interfaces.api.dto.AddAdminReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.LoginAdminReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Author zhenghui
 * @Description 系统管理员DTO转换器
 * @Date 2022/6/26
 */
@Mapper
public interface AdminUserDtoConvert {
    AdminUserDtoConvert INSTANCE = Mappers.getMapper(AdminUserDtoConvert.class);

    @Mappings({
        @Mapping(target = "status", expression = "java(1)")
    })
    CreateAdminUserBo toBo(AddAdminReq addAdminReq);

    LoginAdminUserBo toBo(LoginAdminReq loginAdminReq);
}
