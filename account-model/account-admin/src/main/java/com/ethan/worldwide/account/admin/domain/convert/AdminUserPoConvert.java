package com.ethan.worldwide.account.admin.domain.convert;

import com.ethan.worldwide.account.admin.domain.bo.user.AdminUserBo;
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

    AdminUserBo toBo(AdminUserPo adminUserPo);


    AdminUserPo toPo(AdminUserBo adminUserBo);
}
