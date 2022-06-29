drop table if exists product_category;
create table product_category
(
    id          int auto_increment comment '商品分类编号'
        primary key,
    pid         int           not null comment '父商品分类编号',
    name        varchar(32)   not null comment '分类名称',
    description varchar(255)  null comment '分类描述',
    pic_url     varchar(255)  null comment '分类图片',
    sort        int           not null comment '分类排序',
    status      int default 1 not null comment '状态：0->关闭;1->开启;',
    deleted     int default 1 not null comment '是否被删除：0->已删除;1->未删除;',
    create_time datetime      not null comment '创建时间',
    update_time datetime      null comment '更新时间',
    constraint product_category_id_uindex
        unique (id),
    constraint product_category_name_uindex
        unique (name)
)