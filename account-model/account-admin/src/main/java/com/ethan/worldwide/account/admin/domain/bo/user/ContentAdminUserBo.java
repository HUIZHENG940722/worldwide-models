package com.ethan.worldwide.account.admin.domain.bo.user;

import lombok.Data;

import java.util.Date;

/**
 * @Author zhenghui
 * @Description 后台管理用户内容BO
 * @Date 2022/6/27
 */
@Data
public class ContentAdminUserBo {

    /**
     * 用户编码
     */
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
    private Date LoginTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
