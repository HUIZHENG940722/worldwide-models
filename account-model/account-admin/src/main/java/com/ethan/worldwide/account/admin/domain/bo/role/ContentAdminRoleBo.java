package com.ethan.worldwide.account.admin.domain.bo.role;

import lombok.Data;

import java.util.Date;

/**
 * @Author zhenghui
 * @Description 用户角色内容BO
 * @Date 2022/6/30
 */
@Data
public class ContentAdminRoleBo {
    /**
     * 角色编码
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;
}
