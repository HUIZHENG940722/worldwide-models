package com.ethan.worldwide.mall.product.inter.assembler;

import com.ethan.worldwide.mall.product.domain.bo.category.PageProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.ProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.valueObject.UpdateProductCategoryBo;
import com.ethan.worldwide.openapi.interfaces.api.dto.*;
import com.ethan.worldwide.mall.product.domain.bo.category.PageQueryProductCategoryBo;
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
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "status", expression = "java(com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum.NUMBER_1)"),
        @Mapping(target = "deleted", expression = "java(com.ethan.worldwide.mall.product.infra.dao.enums.DeletedEnum.NUMBER_1)"),
        @Mapping(target = "createTime", expression = "java(new java.util.Date(System.nanoTime()))"),
        @Mapping(target = "updateTime", ignore = true)
    })
    ProductCategoryBo toBo(CreateProductCategoryReq createProductCategoryReq);

    ProductCategoryResp toResp(ProductCategoryBo productCategoryBo);

    UpdateProductCategoryBo toBo(UpdateProductCategoryReq updateProductCategoryReq);

    PageQueryProductCategoryBo toBo(PageQueryProductCategoryReq pageQueryProductCategoryReq);

    PageProductCategoryResp toPageResp(PageProductCategoryBo pageProductCategoryBo);
}
