package com.ethan.worldwide.mall.product.inter.assembler;

import com.ethan.worldwide.mall.product.domain.bo.CreateProductBrandBo;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductBrandReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author zhenghui
 * @Description 商品品牌DTO转换器
 * @Date 2022/6/22
 */
@Mapper
public interface ProductBrandDtoConvert {

    ProductBrandDtoConvert INSTANCE = Mappers.getMapper(ProductBrandDtoConvert.class);


    @Mappings({
        @Mapping(target = "status", expression = "java(com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum.NUMBER_1)")
    })
    CreateProductBrandBo toBo(CreateProductBrandReq createProductBrandReq);
}