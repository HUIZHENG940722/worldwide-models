package com.ethan.worldwide.mall.product.domain.bo.property;

import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.ProductPropertyKeyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.ProductPropertyValueBo;
import lombok.Data;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品规格Bo
 * @Date 2022/7/15
 */
@Data
public class ProductPropertyBo {
    private ProductPropertyKeyBo propertyKey;

    private List<ProductPropertyValueBo> propertyValueList;
}
