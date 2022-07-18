package com.ethan.worldwide.mall.product.domain.bo.property.valueObject;

import lombok.Data;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品规格值对象-规格键分页数据
 * @Date 2022/7/18
 */
@Data
public class PageKeyProductPropertyBo {
    private Integer total;

    private List<ProductPropertyKeyBo> data;
}
