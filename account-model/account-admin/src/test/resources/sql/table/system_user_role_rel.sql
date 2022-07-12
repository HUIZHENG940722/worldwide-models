drop table if exists system_user_role_rel;
create table if not exists system_user_role_rel
(
    id      int auto_increment comment '编码'
        primary key,
    user_id int not null comment '用户编码',
    role_id int not null comment '角色编码'
);

