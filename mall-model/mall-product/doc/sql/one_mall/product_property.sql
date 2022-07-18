create table if not exists `product_property`
(
    `id`        int auto_increment comment '编码' primary key,
    `name`      varchar(32) not null comment '规格键名称',
    `status`    tinyint     not null default 1 comment '规格键状态:0->禁用;1->启用;',
    deleted     int                  default 1 not null comment '是否被删除：0->已删除;1->未删除;',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '更新时间'
) comment '商品规格键表';