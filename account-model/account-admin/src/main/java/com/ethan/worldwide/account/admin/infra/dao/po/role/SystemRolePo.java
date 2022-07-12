package com.ethan.worldwide.account.admin.infra.dao.po.role;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Author zhenghui
 * @Description 后台用户角色PO
 * @Date 2022/6/30
 */
@Data
@TableName(value = "system_role")
public class SystemRolePo {
    /**
     * 角色编码
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private Date updateTime;

    /**
     * 状态
     */
    private Integer status;
}
