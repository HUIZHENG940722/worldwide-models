package com.ethan.worldwide.mall.product.inter.controller;

import com.ethan.worldwide.mall.product.application.service.ProductBrandService;
import com.ethan.worldwide.mall.product.domain.bo.ContentProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.CreateProductBrandBo;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import com.ethan.worldwide.mall.product.inter.assembler.ProductBrandDtoConvert;
import com.ethan.worldwide.openapi.interfaces.api.MallProductBrandApi;
import com.ethan.worldwide.openapi.interfaces.api.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenghui
 * @Description 商品品牌控制器
 * @Date 2022/6/22
 */
@RestController
public class ProductBrandController implements MallProductBrandApi {

    @Autowired
    private ProductBrandService productBrandService;

    @Override
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Integer> createProductBrand(CreateProductBrandReq createProductBrandReq) {
        // 1 数据转换
        CreateProductBrandBo createProductBrandBo = ProductBrandDtoConvert.INSTANCE.toBo(createProductBrandReq);
        // 2 业务
        Integer productBrandId = productBrandService.createProductBrand(createProductBrandBo);
        if (productBrandId == null) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "创建商品品牌失败");
        }
        // 3 返回结果
        return new ResponseEntity<>(productBrandId, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ContentProductBrandResp> getProductBrandContent(Integer brandId) {
        // 1 数据转换
        // 2 业务
        ContentProductBrandBo contentProductBrandBo = productBrandService.getProductBrandContent(brandId);
        // 3 返回结果
        if (contentProductBrandBo == null) {
            MallProductServiceException.assertException(HttpStatus.NOT_FOUND, "获取商品品牌失败");
        }
        ContentProductBrandResp contentProductBrandResp = ProductBrandDtoConvert.INSTANCE.toContentResp(contentProductBrandBo);
        return new ResponseEntity<>(contentProductBrandResp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageProductBrandResp> pageProductBrandContent(String contentType, PageQueryProductBrandReq pageQueryProductBrandReq) {
        return null;
    }

    @Override
    public ResponseEntity<Integer> updateProductBrand(Integer brandId, UpdateProductBrandReq updateProductBrandReq) {
        return null;
    }
}
