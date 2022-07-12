package com.ethan.worldwide.account.admin.domain.convert;

import com.ethan.worldwide.account.admin.domain.bo.user.SystemUserBo;
import com.ethan.worldwide.account.admin.infra.dao.po.user.SystemUserPo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author zhenghui
 * @Description 系统管理员PO转换器
 * @Date 2022/6/26
 */
@Mapper
public interface SystemUserPoConvert {
    SystemUserPoConvert INSTANCE = Mappers.getMapper(SystemUserPoConvert.class);

    SystemUserBo toBo(SystemUserPo systemUserPo);


    SystemUserPo toPo(SystemUserBo systemUserBo);
}
