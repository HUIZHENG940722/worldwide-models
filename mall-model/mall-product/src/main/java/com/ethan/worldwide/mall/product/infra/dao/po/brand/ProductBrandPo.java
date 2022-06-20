package com.ethan.worldwide.mall.product.infra.dao.po.brand;

import com.baomidou.mybatisplus.annotation.*;
import com.ethan.worldwide.mall.product.infra.dao.enums.DeletedEnum;
import com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhenghui
 * @Description 商品品牌PO
 * @Date 2022/6/22
 */
@Data
@TableName(value = "product_brand")
public class ProductBrandPo implements Serializable {

    /**
     * 品牌编号（主键）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌描述
     */
    private String description;

    /**
     * 品牌名图片
     */
    private String picUrl;

    /**
     * 状态
     */
    @EnumValue
    private StatusEnum status;

    /**
     * 是否被删除：0->已删除;1->未删除;
     */
    @EnumValue
    private DeletedEnum deleted;

    /**
     * 创建时间
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private Date updateTime;
}
