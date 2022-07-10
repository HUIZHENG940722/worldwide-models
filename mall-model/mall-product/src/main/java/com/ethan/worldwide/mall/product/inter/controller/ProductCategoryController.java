package com.ethan.worldwide.mall.product.inter.controller;

import com.ethan.worldwide.mall.product.application.service.ProductCategoryService;
import com.ethan.worldwide.mall.product.domain.bo.category.PageProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.ProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.valueObject.UpdateProductCategoryBo;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import com.ethan.worldwide.mall.product.inter.assembler.ProductCategoryDtoConvert;
import com.ethan.worldwide.openapi.interfaces.api.MallProductCategoryApi;
import com.ethan.worldwide.openapi.interfaces.api.dto.*;
import com.ethan.worldwide.mall.product.domain.bo.category.PageQueryProductCategoryBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zWX1058539
 * @Description 商品分类控制器
 * @Date 2022/6/29
 */
@RestController
public class ProductCategoryController implements MallProductCategoryApi {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public ResponseEntity<Integer> createProductCategory(CreateProductCategoryReq createProductCategoryReq) {
        // 1 数据转换
         ProductCategoryBo productCategoryBo = ProductCategoryDtoConvert.INSTANCE.toBo(createProductCategoryReq);
        // 2 业务
        Integer categoryId = productCategoryService.createProductCategory(productCategoryBo);
        // 3 返回结果
        if (categoryId == null) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "创建商品分类失败");
        }
        return new ResponseEntity<>(categoryId, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductCategoryResp> getProductCategoryContent(Integer categoryId) {
        // 1 数据转换
        // 2 业务
        ProductCategoryBo productCategoryBo = productCategoryService.getProductCategoryContent(categoryId);
        // 3 返回结果
        if (productCategoryBo == null) {
            MallProductServiceException.assertException(HttpStatus.NOT_FOUND, "获取商品分类内容失败");
        }
        ProductCategoryResp productCategoryResp = ProductCategoryDtoConvert.INSTANCE.toResp(productCategoryBo);
        return new ResponseEntity<>(productCategoryResp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageProductCategoryResp> pageProductCategoryContent(PageQueryProductCategoryReq pageQueryProductCategoryReq) {
        // 1 数据转换
        PageQueryProductCategoryBo pageQueryProductCategoryBo = ProductCategoryDtoConvert.INSTANCE.toBo(pageQueryProductCategoryReq);
        // 2 业务
        PageProductCategoryBo pageProductCategoryBo = productCategoryService.pageProductCategoryContent(pageQueryProductCategoryBo);
        // 3 返回结果
        PageProductCategoryResp pageProductCategoryResp = ProductCategoryDtoConvert.INSTANCE.toPageResp(pageProductCategoryBo);
        return new ResponseEntity<>(pageProductCategoryResp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> updateProductCategory(Integer categoryId, UpdateProductCategoryReq updateProductCategoryReq) {
        // 1 数据转换
        UpdateProductCategoryBo updateProductCategoryBo = ProductCategoryDtoConvert.INSTANCE.toBo(updateProductCategoryReq);
        // 2 业务
        Integer update = productCategoryService.updateProductCategory(categoryId, updateProductCategoryBo);
        // 3 返回结果
        if (update == null) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "更新商品分类失败");
        }
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
}
