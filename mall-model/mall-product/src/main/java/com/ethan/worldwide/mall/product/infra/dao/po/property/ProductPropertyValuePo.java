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
 * @Description 商品规格值PO
 * @Date 2022/7/18
 */
@Data
@TableName(value = "product_property_value")
public class ProductPropertyValuePo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 规格编码
     */
    private Integer propertyId;

    /**
     * 规格值名称
     */
    private String name;

    /**
     * 规格值状态:0->禁用;1->启用;
     */
    private Integer status;

    private Integer deleted;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Date createTime;

    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private Date updateTime;
}
