drop table if exists system_user;
create table if not exists system_user
(
    id          int auto_increment comment '用户编码'
        primary key,
    username    varchar(32)   not null comment '用户账号' unique,
    password    varchar(64)   not null comment '用户密码',
    status      int default 1 not null comment '是否启用:0->启用;1->禁用;',
    deleted     int default 1 not null comment '是否删除:0-已删除;1-未删除;',
    login_time  datetime      null comment '登录时间',
    create_time datetime      not null comment '创建时间',
    update_time datetime      null comment '更新时间'
);

