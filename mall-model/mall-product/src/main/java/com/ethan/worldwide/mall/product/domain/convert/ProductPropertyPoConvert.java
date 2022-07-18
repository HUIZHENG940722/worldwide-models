package com.ethan.worldwide.mall.product.domain.convert;

import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.ProductPropertyKeyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.UpdateProductPropertyKeyBo;
import com.ethan.worldwide.mall.product.infra.dao.po.property.ProductPropertyPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品规格PO转换器
 * @Date 2022/7/12
 */
@Mapper
public interface ProductPropertyPoConvert {

    ProductPropertyPoConvert INSTANCE = Mappers.getMapper(ProductPropertyPoConvert.class);


    ProductPropertyKeyBo toBo(ProductPropertyPo productPropertyPo);

    @Mappings({
        @Mapping(target = "id", ignore = true)
    })
    ProductPropertyPo toPo(ProductPropertyKeyBo attrKeyBo);

    ProductPropertyPo updateToPo(UpdateProductPropertyKeyBo updateProductPropertyKeyBo);

    List<ProductPropertyKeyBo> toBo(List<ProductPropertyPo> records);
}
