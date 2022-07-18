package com.ethan.worldwide.mall.product.inter.controller;

import com.ethan.worldwide.openapi.interfaces.api.MallProductSpuApi;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductSpuAndSkuReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zWX1058539
 * @Description 商品SPU用户接口层
 * @Date 2022/7/12
 */
@RestController
public class ProductSpuController implements MallProductSpuApi {
    @Override
    public ResponseEntity<Integer> createProductSpu(CreateProductSpuAndSkuReq createProductSpuAndSkuReq) {
        return null;
    }
}
