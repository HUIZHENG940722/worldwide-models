package com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.valueObject;

import lombok.Data;

import java.util.Date;

/**
 * @Author zWX1058539
 * @Description 更新商品规格值值对象
 * @Date 2022/7/18
 */
@Data
public class UpdateProductPropertyValueBo {

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
}
