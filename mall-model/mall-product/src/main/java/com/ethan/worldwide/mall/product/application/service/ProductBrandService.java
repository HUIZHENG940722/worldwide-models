package com.ethan.worldwide.mall.product.application.service;

import com.ethan.worldwide.mall.product.domain.bo.CreateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.service.ProductBrandDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhenghui
 * @Description 商品品牌应用服务
 * @Date 2022/6/22
 */
@Service
public class ProductBrandService {

    @Autowired
    private ProductBrandDomainService productBrandDomainService;

    /**
     * 创建商品品牌
     *
     * @param createProductBrandBo
     * @return
     */
    public Integer createProductBrand(CreateProductBrandBo createProductBrandBo) {
        // 1 校验
        // 2 业务
        // 3 返回结果
        return productBrandDomainService.create(createProductBrandBo);
    }
}
