package com.ethan.worldwide.mall.product.domain.service;

import com.ethan.worldwide.mall.product.domain.bo.category.ContentProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.CreateProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.QueryProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.repository.ProductCategoryRepository;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zWX1058539
 * @Description 商品分类领域服务
 * @Date 2022/6/29
 */
@Service
public class ProductCategoryDomainService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 创建商品分类
     *
     * @param createProductCategoryBo
     * @return
     */
    @Transactional
    public Integer create(CreateProductCategoryBo createProductCategoryBo) {
        // 1 核心校验
        // 1.1 校验商品分类父编码是否存在
        checkLegalityParent(createProductCategoryBo.getPid());
        // 1.2 校验商品分类名称是否重复
        QueryProductCategoryBo queryProductCategoryBo = new QueryProductCategoryBo();
        queryProductCategoryBo.setName(createProductCategoryBo.getName());
        ContentProductCategoryBo contentProductCategoryBo = productCategoryRepository.get(queryProductCategoryBo);
        if (contentProductCategoryBo != null) {
            MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品分类名称重复");
        }
        // 2 核心业务
        return productCategoryRepository.add(createProductCategoryBo);
        // 3 返回结果
    }

    public ContentProductCategoryBo get(QueryProductCategoryBo queryProductCategoryBo) {
        // 1 核心校验
        // 2 核心业务
        return productCategoryRepository.get(queryProductCategoryBo);
        // 3 返回结果
    }

    private void checkLegalityParent(Integer pid) {
        if (!pid.equals(0)) {
            QueryProductCategoryBo queryProductCategoryBo = new QueryProductCategoryBo();
            queryProductCategoryBo.setId(pid);
            ContentProductCategoryBo contentProductCategoryBo = productCategoryRepository.get(queryProductCategoryBo);
            if (contentProductCategoryBo == null) {
                MallProductServiceException.assertException(HttpStatus.NOT_FOUND, "商品分类父编码非法");
            }
        }
    }
}