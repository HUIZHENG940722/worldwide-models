package com.ethan.worldwide.mall.product.domain.convert;

import com.ethan.worldwide.mall.product.domain.bo.category.ProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.valueObject.UpdateProductCategoryBo;
import com.ethan.worldwide.mall.product.infra.dao.po.category.ProductCategoryPo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createTime", ignore = true),
        @Mapping(target = "updateTime", expression = "java(new java.util.Date(System.currentTimeMillis()))")
    })
    ProductCategoryPo updateBoToPo(UpdateProductCategoryBo updateProductCategoryBo);

    ProductCategoryPo toPo(ProductCategoryBo productCategoryBo);

    ProductCategoryBo toBo(ProductCategoryPo productCategoryPo);

    List<ProductCategoryBo> toBo(List<ProductCategoryPo> productCategoryPoList);
}
