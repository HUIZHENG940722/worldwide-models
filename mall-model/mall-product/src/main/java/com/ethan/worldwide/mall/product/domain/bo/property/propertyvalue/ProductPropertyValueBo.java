package com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue;

import lombok.Data;

import java.util.Date;

/**
 * @Author zWX1058539
 * @Description 商品规格值BO
 * @Date 2022/7/18
 */
@Data
public class ProductPropertyValueBo {

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

    private Date createTime;

    private Date updateTime;
}
