package com.ethan.worldwide.mall.product.domain.convert;

import com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.ProductPropertyValueBo;
import com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.valueObject.UpdateProductPropertyValueBo;
import com.ethan.worldwide.mall.product.infra.dao.po.property.ProductPropertyValuePo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品规格值PO转换器
 * @Date 2022/7/18
 */
@Mapper
public interface ProductPropertyValuePoConvert {

    ProductPropertyValuePoConvert INSTANCE = Mappers.getMapper(ProductPropertyValuePoConvert.class);

    List<ProductPropertyValuePo> toPo(List<ProductPropertyValueBo> propertyValueBoList);

    List<ProductPropertyValueBo> toBo(List<ProductPropertyValuePo> propertyValuePoList);

    ProductPropertyValuePo toPo(ProductPropertyValueBo productPropertyValueBo);

    @Mappings({
        @Mapping(target = "deleted", expression = "java(1)"),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "updateTime", expression = "java(new java.util.Date(System.currentTimeMillis()))")
    })
    ProductPropertyValuePo updateToPo(UpdateProductPropertyValueBo updateProductPropertyValueBo);
}
