package com.ethan.worldwide.account.admin.domain.bo.role;

import lombok.Data;

/**
 * @Author zhenghui
 * @Description 查询后台用户角色BO
 * @Date 2022/7/3
 */
@Data
public class QuerySystemRoleBo {
    /**
     * 角色编码
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;
}
