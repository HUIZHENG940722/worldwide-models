package com.ethan.worldwide.mall.product.application.service;

import com.ethan.worldwide.mall.product.domain.bo.category.PageProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.ProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.valueObject.UpdateProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.PageQueryProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.QueryProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.service.ProductCategoryDomainService;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    public Integer createProductCategory(ProductCategoryBo productCategoryBo) {
        // 1 校验
        // 2 业务
        return productCategoryDomainService.create(productCategoryBo);
        // 3 返回结果
    }

    public ProductCategoryBo getProductCategoryContent(Integer categoryId) {
        // 1 校验
        // 1.1 校验商品分类是否存在
        checkProductCategory(categoryId);
        // 2 业务
        QueryProductCategoryBo queryProductCategoryBo = new QueryProductCategoryBo();
        queryProductCategoryBo.setId(categoryId);
        return productCategoryDomainService.get(queryProductCategoryBo);
        // 3 返回结果
    }

    public Integer updateProductCategory(Integer categoryId, UpdateProductCategoryBo updateProductCategoryBo) {
        // 1 校验
        // 1.1 校验商品分类是否存在
        ProductCategoryBo byId = checkProductCategory(categoryId);
        // 2 业务
        return productCategoryDomainService.updateById(byId, updateProductCategoryBo);
        // 3 返回结果
    }

    public PageProductCategoryBo pageProductCategoryContent(PageQueryProductCategoryBo pageQueryProductCategoryBo) {
        // 1 校验
        // 2 业务
        return productCategoryDomainService.page(pageQueryProductCategoryBo);
        // 3 返回结果
    }

    private ProductCategoryBo checkProductCategory(Integer categoryId) {
        QueryProductCategoryBo queryById = new QueryProductCategoryBo();
        queryById.setId(categoryId);
        ProductCategoryBo byId = productCategoryDomainService.get(queryById);
        if (byId == null) {
            MallProductServiceException.assertException(HttpStatus.NOT_FOUND, "商品分类不存在");
        }
        return byId;
    }
}
