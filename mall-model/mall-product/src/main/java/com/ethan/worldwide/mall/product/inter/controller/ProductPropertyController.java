package com.ethan.worldwide.mall.product.inter.controller;

import com.ethan.worldwide.mall.product.application.service.ProductPropertyService;
import com.ethan.worldwide.mall.product.domain.bo.property.PageProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.PageQueryKeyProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.ProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.UpdateProductPropertyBo;
import com.ethan.worldwide.mall.product.inter.assembler.ProductPropertyDtoConvert;
import com.ethan.worldwide.openapi.interfaces.api.MallProductPropertyApi;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductPropertyReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.PageProductPropertyResp;
import com.ethan.worldwide.openapi.interfaces.api.dto.PageQueryProductPropertyReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.ProductPropertyResq;
import com.ethan.worldwide.openapi.interfaces.api.dto.UpdateProductPropertyReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @Author zWX1058539
 * @Description 商品规格用户接口
 * @Date 2022/7/12
 */
public class ProductPropertyController implements MallProductPropertyApi {

    @Autowired
    private ProductPropertyService productPropertyService;


    @Override
    public ResponseEntity<Integer> createProductProperty(CreateProductPropertyReq createProductPropertyReq) {
        // 1 数据转换
        ProductPropertyBo productPropertyBo = ProductPropertyDtoConvert.INSTANCE.toBo(createProductPropertyReq);
        // 2 业务
        Integer propertyId = productPropertyService.createProductProperty(productPropertyBo);
        // 3 返回结果
        return new ResponseEntity<>(propertyId, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductPropertyResq> getProductProperty(Integer propertyKeyId) {
        // 1 数据转换
        // 2 业务
        ProductPropertyBo productPropertyBo = productPropertyService.getProductProperty(propertyKeyId);
        // 3 返回结果
        ProductPropertyResq productPropertyResq = ProductPropertyDtoConvert.INSTANCE.toResp(productPropertyBo);
        return new ResponseEntity<>(productPropertyResq, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageProductPropertyResp> pageProductProperty(PageQueryProductPropertyReq pageQueryProductPropertyReq) {
        // 1 数据转换
        PageQueryKeyProductPropertyBo pageQueryKeyProductPropertyBo = ProductPropertyDtoConvert.INSTANCE.toBo(pageQueryProductPropertyReq);
        // 2 业务
        PageProductPropertyBo pageProductPropertyBo = productPropertyService.pageProductProperty(pageQueryKeyProductPropertyBo);
        // 3 返回结果
        PageProductPropertyResp pageProductPropertyResp = ProductPropertyDtoConvert.INSTANCE.toResp(pageProductPropertyBo);
        return new ResponseEntity<>(pageProductPropertyResp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> updateProductProperty(Integer propertyKeyId, UpdateProductPropertyReq updateProductPropertyReq) {
        // 1 数据转换
        UpdateProductPropertyBo updateProductPropertyBo = ProductPropertyDtoConvert.INSTANCE.toBo(updateProductPropertyReq);
        // 2 业务
        Integer update = productPropertyService.updateProductProperty(propertyKeyId, updateProductPropertyBo);
        // 3 返回结果
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
}
