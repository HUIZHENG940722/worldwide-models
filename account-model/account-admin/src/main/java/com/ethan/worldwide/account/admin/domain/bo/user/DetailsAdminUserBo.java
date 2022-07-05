package com.ethan.worldwide.account.admin.domain.bo.user;

import com.ethan.worldwide.account.admin.domain.bo.role.ContentAdminRoleBo;
import lombok.Data;

import java.util.List;

/**
 * @Author zhenghui
 * @Description 后台用户详情内容
 * @Date 2022/7/3
 */
@Data
public class DetailsAdminUserBo {
    private ContentAdminUserBo contentAdminUserBo;

    private List<ContentAdminRoleBo> contentAdminRoleBoList;
}
