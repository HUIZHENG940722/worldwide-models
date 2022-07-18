package com.ethan.worldwide.mall.product.domain.bo.property.valueObject;

import lombok.Data;

import java.util.Date;

/**
 * @Author zWX1058539
 * @Description 更新商品分类值对象
 * @Date 2022/7/12
 */
@Data
public class UpdateProductPropertyKeyBo {

    /**
     * 规格键名称
     */
    private String name;

    /**
     * 规格键状态:0->禁用;1->启用;
     */
    private Integer status;
}
