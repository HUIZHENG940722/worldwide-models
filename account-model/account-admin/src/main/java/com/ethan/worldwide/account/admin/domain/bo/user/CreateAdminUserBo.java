package com.ethan.worldwide.account.admin.domain.bo.user;

import lombok.Data;

/**
 * @Author zhenghui
 * @Description 创建管理员用户BO
 * @Date 2022/6/27
 */
@Data
public class CreateAdminUserBo {

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
}
