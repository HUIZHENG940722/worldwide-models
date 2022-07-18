create table if not exists product_brand
(
    id          int auto_increment comment '商品品牌编号',
    name        varchar(32)   not null comment '品牌名称',
    description varchar(255)  null comment '品牌描述',
    pic_url     varchar(255)  null comment '品牌名图片',
    status      int default 1 not null comment '状态：0->关闭;1->开启;',
    deleted     int default 1 not null comment '是否被删除：0->已删除;1->未删除;',
    create_time datetime      not null comment '创建时间',
    update_time datetime      null comment '更新时间',
    constraint product_brand_id_uindex
        unique (id),
    constraint product_brand_name_uindex
        unique (name)
)
comment '商品品牌表';

alter table product_brand
    add primary key (id);

