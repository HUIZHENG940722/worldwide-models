package com.ethan.worldwide.mall.product.inter.assembler;

import com.ethan.worldwide.mall.product.domain.bo.brand.ProductBrandBo;
import com.ethan.worldwide.openapi.interfaces.api.dto.*;
import com.ethan.worldwide.mall.product.domain.bo.brand.PageProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.PageQueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.valueObject.UpdateProductBrandBo;
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
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "status", expression = "java(com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum.NUMBER_1)"),
        @Mapping(target = "deleted", expression = "java(com.ethan.worldwide.mall.product.infra.dao.enums.DeletedEnum.NUMBER_1)"),
        @Mapping(target = "createTime", expression = "java(new java.util.Date(System.currentTimeMillis()))"),
        @Mapping(target = "updateTime", ignore = true)
    })
    ProductBrandBo toBo(CreateProductBrandReq createProductBrandReq);

    ProductBrandResp toResp(ProductBrandBo productBrandBo);

    UpdateProductBrandBo toBo(UpdateProductBrandReq updateProductBrandReq);

    PageQueryProductBrandBo toBo(PageQueryProductBrandReq pageQueryProductBrandReq);

    PageProductBrandResp toPageContent(PageProductBrandBo pageProductBrandBo);
}
