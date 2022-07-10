package com.ethan.worldwide.mall.product.domain.service;

import com.ethan.worldwide.mall.product.domain.bo.brand.PageProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.PageQueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.ProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.QueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.valueObject.UpdateProductBrandBo;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import com.ethan.worldwide.mall.product.domain.repository.ProductBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhenghui
 * @Description 商品品牌领域服务
 * @Date 2022/6/22
 */
@Service
public class ProductBrandDomainService {

    @Autowired
    private ProductBrandRepository productBrandRepository;

    /**
     * 创建商品品牌
     *
     * @param productBrandBo
     * @return
     */
    @Transactional
    public Integer create(ProductBrandBo productBrandBo) {
        // 1 核心校验
        // 1.1 验证商品品牌是否重复
        checkName(null, productBrandBo.getName());
        // 2 核心业务
        // 2.1 插入数据
        return productBrandRepository.add(productBrandBo);
        // 3 返回结果
    }

    /**
     * 获取商品品牌内容
     *
     * @param queryProductBrandBo
     * @return
     */
    public ProductBrandBo get(QueryProductBrandBo queryProductBrandBo) {
        // 1 核心校验
        // 2 核心业务
        return productBrandRepository.get(queryProductBrandBo);
        // 3 返回结果
    }

    /**
     * 更新商品品牌内容
     *
     * @param productBrandBo
     * @param updateProductBrandBo
     * @return
     */
    @Transactional
    public Integer updateById(ProductBrandBo productBrandBo, UpdateProductBrandBo updateProductBrandBo) {
        // 1 核心校验
        // 1.1 验证商品品牌是否重复
        checkName(productBrandBo, updateProductBrandBo.getName());
        // 2 核心业务
        return productBrandRepository.updateById(productBrandBo.getId(), updateProductBrandBo);
        // 3 返回结果
    }


    /**
     * 分页获取商品品牌内容
     *
     * @param pageQueryProductBrandBo
     * @return
     */
    public PageProductBrandBo page(PageQueryProductBrandBo pageQueryProductBrandBo) {
        // 1 核心校验
        // 2 核心业务
        return productBrandRepository.page(pageQueryProductBrandBo);
        // 3 返回结果
    }

    private void checkName(ProductBrandBo productBrandBo, String name) {
        QueryProductBrandBo queryProductBrandBo = new QueryProductBrandBo();
        queryProductBrandBo.setName(name);
        ProductBrandBo byName = productBrandRepository.get(queryProductBrandBo);
        if (productBrandBo != null) {
            if (byName != null && !byName.getId().equals(productBrandBo.getId())) {
                MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品品牌名称重复");
            }
        } else {
            if (byName != null) {
                MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品品牌名称重复");
            }
        }
    }
}
