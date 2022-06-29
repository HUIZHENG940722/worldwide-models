package com.ethan.worldwide.mall.product.domain.convert;

import com.ethan.worldwide.mall.product.domain.bo.brand.ContentProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.CreateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.UpdateProductBrandBo;
import com.ethan.worldwide.mall.product.infra.dao.po.brand.ProductBrandPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author zhenghui
 * @Description 商品品牌PO转换器
 * @Date 2022/6/22
 */
@Mapper
public interface ProductBrandPoConvert {

    ProductBrandPoConvert INSTANCE = Mappers.getMapper(ProductBrandPoConvert.class);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "deleted", expression = "java(com.ethan.worldwide.mall.product.infra.dao.enums.DeletedEnum.NUMBER_1)"),
        @Mapping(target = "createTime", expression = "java(new java.util.Date(System.currentTimeMillis()))"),
        @Mapping(target = "updateTime", expression = "java(new java.util.Date(System.currentTimeMillis()))")
    })
    ProductBrandPo createBoToPo(CreateProductBrandBo createProductBrandBo);

    ContentProductBrandBo toContentBo(ProductBrandPo productBrandPo);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "updateTime", expression = "java(new java.util.Date(System.currentTimeMillis()))")
    })
    ProductBrandPo updateBoToPo(UpdateProductBrandBo updateProductBrandBo);

    List<ContentProductBrandBo> toContentBo(List<ProductBrandPo> records);
}
