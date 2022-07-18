package com.ethan.worldwide.mall.product.inter.assembler;

import com.ethan.worldwide.mall.product.domain.bo.property.PageProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.PageQueryKeyProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.ProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.UpdateProductPropertyBo;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductPropertyReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.PageProductPropertyResp;
import com.ethan.worldwide.openapi.interfaces.api.dto.PageQueryProductPropertyReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.ProductPropertyResq;
import com.ethan.worldwide.openapi.interfaces.api.dto.UpdateProductPropertyReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author zWX1058539
 * @Description 商品属性键DTO转换器
 * @Date 2022/7/12
 */
@Mapper
public interface ProductPropertyDtoConvert {

    ProductPropertyDtoConvert INSTANCE = Mappers.getMapper(ProductPropertyDtoConvert.class);

    ProductPropertyBo toBo(CreateProductPropertyReq createProductPropertyReq);

    ProductPropertyResq toResp(ProductPropertyBo productPropertyBo);

    PageQueryKeyProductPropertyBo toBo(PageQueryProductPropertyReq pageQueryProductPropertyReq);

    PageProductPropertyResp toResp(PageProductPropertyBo pageProductPropertyBo);

    UpdateProductPropertyBo toBo(UpdateProductPropertyReq updateProductPropertyReq);
}
