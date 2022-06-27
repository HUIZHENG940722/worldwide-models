package com.ethan.worldwide.mall.product.inter.controller;

import com.ethan.worldwide.mall.product.application.service.ProductBrandService;
import com.ethan.worldwide.mall.product.domain.bo.ContentProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.CreateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.PageProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.PageQueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.UpdateProductBrandBo;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import com.ethan.worldwide.mall.product.inter.assembler.ProductBrandDtoConvert;
import com.ethan.worldwide.openapi.interfaces.api.MallProductBrandApi;
import com.ethan.worldwide.openapi.interfaces.api.dto.ContentProductBrandResp;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductBrandReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.PageProductBrandResp;
import com.ethan.worldwide.openapi.interfaces.api.dto.PageQueryProductBrandReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.UpdateProductBrandReq;
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
    public ResponseEntity<PageProductBrandResp> pageProductBrandContent(PageQueryProductBrandReq pageQueryProductBrandReq) {
        // 1 数据转换
        PageQueryProductBrandBo pageQueryProductBrandBo = ProductBrandDtoConvert.INSTANCE.toBo(pageQueryProductBrandReq);
        // 2 业务
        PageProductBrandBo pageProductBrandBo = productBrandService.pageProductBrandContent(pageQueryProductBrandBo);
        // 3 返回结果
        PageProductBrandResp pageProductBrandResp = ProductBrandDtoConvert.INSTANCE.toPageContent(pageProductBrandBo);
        return new ResponseEntity<>(pageProductBrandResp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> updateProductBrand(Integer brandId, UpdateProductBrandReq updateProductBrandReq) {
        // 1 数据转换
        UpdateProductBrandBo updateProductBrandBo = ProductBrandDtoConvert.INSTANCE.toBo(updateProductBrandReq);
        // 2 业务
        Integer update = productBrandService.updateProductById(brandId, updateProductBrandBo);
        // 3 返回结果
        if (update == null) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "更新商品品牌失败");
        }
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
}
