package com.ethan.worldwide.mall.product.inter.assembler;

import com.ethan.worldwide.mall.product.domain.bo.category.ContentProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.CreateProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.UpdateProductCategoryBo;
import com.ethan.worldwide.openapi.interfaces.api.dto.ContentProductCategoryResp;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductCategoryReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.UpdateProductCategoryReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Author zWX1058539
 * @Description 商品分类DTO转换器
 * @Date 2022/6/29
 */
@Mapper
public interface ProductCategoryDtoConvert {

    ProductCategoryDtoConvert INSTANCE = Mappers.getMapper(ProductCategoryDtoConvert.class);

    @Mappings({
        @Mapping(target = "status", expression = "java(com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum.NUMBER_1)")
    })
    CreateProductCategoryBo toBo(CreateProductCategoryReq createProductCategoryReq);

    ContentProductCategoryResp toContentResp(ContentProductCategoryBo contentProductCategoryBo);

    UpdateProductCategoryBo toBo(UpdateProductCategoryReq updateProductCategoryReq);
}
