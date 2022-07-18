package com.ethan.worldwide.mall.product.domain.bo.property.valueObject;

import com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.valueObject.UpdateProductPropertyValueBo;
import lombok.Data;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 更新商品规格值对象
 * @Date 2022/7/18
 */
@Data
public class UpdateProductPropertyBo {
    private UpdateProductPropertyKeyBo propertyKey;

    private List<UpdateProductPropertyValueBo> productPropertyValueList;
}
