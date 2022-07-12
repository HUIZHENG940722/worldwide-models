package com.ethan.worldwide.account.admin.infra.dao.po.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Author zhenghui
 * @Description 系统管理员PO
 * @Date 2022/6/26
 */
@Data
@TableName(value = "system_user")
public class SystemUserPo {
    /**
     * 用户编码
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    /**
     * 状态：0-未启用;1->已启用;
     */
    private Integer status;


    /**
     * 是否被删除：0->已删除;1->未删除;
     */
    private Integer deleted;

    /**
     * 最后登录时间
     */
    private Date loginTime;

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
}
