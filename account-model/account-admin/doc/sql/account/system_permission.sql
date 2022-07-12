create table if not exists system_permission
(
    id              int          not null comment '编码',
    name            varchar(50)  not null,
    permission      varchar(100) not null comment '权限值',
    permission_type tinyint      not null comment '权限类型:0->菜单;1->操作;',
    status          tinyint      not null comment '是否启用:0->禁用;1->启用;',
    create_time     datetime     not null comment '创建时间',
    update_time     datetime     null comment '更新时间',
    deleted         bit          not null comment '是否删除:0->已删除;1->未删除'
)
    comment '后台管理权限表';

