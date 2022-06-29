package com.ethan.worldwide.mall.product.application.service;

import com.ethan.worldwide.mall.product.domain.bo.category.ContentProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.CreateProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.QueryProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.service.ProductCategoryDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zWX1058539
 * @Description 商品分类应用服务
 * @Date 2022/6/29
 */
@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryDomainService productCategoryDomainService;


    public Integer createProductCategory(CreateProductCategoryBo createProductCategoryBo) {
        // 1 校验
        // 2 业务
        return productCategoryDomainService.create(createProductCategoryBo);
        // 3 返回结果
    }

    public ContentProductCategoryBo getProductCategoryContent(Integer categoryId) {
        // 1 校验
        // 2 业务
        QueryProductCategoryBo queryProductCategoryBo = new QueryProductCategoryBo();
        queryProductCategoryBo.setId(categoryId);
        return productCategoryDomainService.get(queryProductCategoryBo);
        // 3 返回结果
    }
}
