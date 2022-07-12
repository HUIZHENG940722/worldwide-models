package com.ethan.worldwide.account.admin.inter.assembler;

import com.ethan.worldwide.account.admin.domain.bo.user.SystemUserBo;
import com.ethan.worldwide.account.admin.domain.bo.user.LoginSystemUserBo;
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
public interface SystemUserDtoConvert {
    SystemUserDtoConvert INSTANCE = Mappers.getMapper(SystemUserDtoConvert.class);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "status", expression = "java(1)"),
        @Mapping(target = "deleted", expression = "java(1)"),
        @Mapping(target = "loginTime", ignore = true),
        @Mapping(target = "createTime", expression = "java(new java.util.Date(System.currentTimeMillis()))"),
        @Mapping(target = "updateTime", ignore = true)
    })
    SystemUserBo toBo(AddAdminReq addAdminReq);

    LoginSystemUserBo toBo(LoginAdminReq loginAdminReq);
}
