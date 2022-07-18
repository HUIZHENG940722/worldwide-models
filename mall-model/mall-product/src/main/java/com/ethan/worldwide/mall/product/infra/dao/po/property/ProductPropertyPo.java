package com.ethan.worldwide.mall.product.infra.dao.po.property;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author zWX1058539
 * @Description 商品规格PO
 * @Date 2022/7/12
 */
@Data
@TableName(value = "product_property")
public class ProductPropertyPo {

    /**
     * 规格编码
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 规格状态:0->禁用;1->启用;
     */
    private Integer status;

    private Integer deleted;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date createTime;

    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private Date updateTime;
}
