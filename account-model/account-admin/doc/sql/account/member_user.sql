create table if not exists member_user
(
    id          int auto_increment comment '用户编码'
        primary key,
    nick_name   varchar(32)   null comment '用户昵称',
    avatar      varchar(255)  null comment '用户头像',
    status      int default 1 not null comment '用户状态：0->关闭;1->开启;',
    mobile      varchar(32)   not null comment '手机号',
    username    varchar(32)   not null comment '登录名',
    password    varchar(255)  not null comment '用户密码',
    register_ip varchar(32)   not null comment '注册IP',
    login_ip    varchar(32)   null comment '登录IP',
    login_time  datetime      null comment '最后登录时间',
    deleted     int default 1 not null comment '是否删除：0->已删除;1->未删除;',
    create_time datetime      not null comment '创建时间',
    update_time datetime      null comment '更新时间',
    constraint member_user_id_uindex
        unique (id)
)
    comment '会员用户';

