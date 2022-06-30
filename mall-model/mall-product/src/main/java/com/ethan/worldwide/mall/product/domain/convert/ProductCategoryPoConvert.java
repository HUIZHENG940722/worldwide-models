package com.ethan.worldwide.mall.product.domain.convert;

import com.ethan.worldwide.mall.product.domain.bo.category.ContentProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.CreateProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.UpdateProductCategoryBo;
import com.ethan.worldwide.mall.product.infra.dao.po.category.ProductCategoryPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Author zWX1058539
 * @Description 商品分类PO转换器
 * @Date 2022/6/29
 */
@Mapper
public interface ProductCategoryPoConvert {

    ProductCategoryPoConvert INSTANCE = Mappers.getMapper(ProductCategoryPoConvert.class);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "deleted", expression = "java(com.ethan.worldwide.mall.product.infra.dao.enums.DeletedEnum.NUMBER_1)"),
        @Mapping(target = "createTime", expression = "java(new java.util.Date(System.currentTimeMillis()))"),
        @Mapping(target = "updateTime", ignore = true)
    })
    ProductCategoryPo createBoToPo(CreateProductCategoryBo createProductCategoryBo);

    ContentProductCategoryBo toContentBo(ProductCategoryPo productCategoryPo);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "updateTime", expression = "java(new java.util.Date(System.currentTimeMillis()))")
    })
    ProductCategoryPo updateBoToPo(UpdateProductCategoryBo updateProductCategoryBo);
}
