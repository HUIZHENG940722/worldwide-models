package com.ethan.worldwide.account.admin.domain.bo.role;

import lombok.Data;

import java.util.Date;

/**
 * @Author zhenghui
 * @Description 创建后台用户角色BO
 * @Date 2022/6/30
 */
@Data
public class CreateAdminRoleBo {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;


    /**
     * 状态
     */
    private Integer status;
}
