drop table if exists system_role;
create table if not exists system_role
(
    id          int auto_increment comment '角色编码'
        primary key,
    name        varchar(32)       not null comment '角色名称' unique,
    description varchar(255)      null comment '描述',
    create_time datetime          not null comment '创建时间',
    update_time datetime          null comment '更新时间',
    status      tinyint default 1 not null comment '状态：0-禁用;1-启用;'
);

