package com.ethan.worldwide.mall.product.domain.service;

import cn.hutool.core.util.StrUtil;
import com.ethan.worldwide.mall.product.domain.bo.category.PageProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.ProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.valueObject.UpdateProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.repository.ProductCategoryRepository;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import com.ethan.worldwide.mall.product.domain.bo.category.PageQueryProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.QueryProductCategoryBo;
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
     * @param productCategoryBo
     * @return
     */
    @Transactional
    public Integer create(ProductCategoryBo productCategoryBo) {
        // 1 核心校验
        // 1.1 校验商品分类父编码是否存在
        checkPid(productCategoryBo.getPid());
        // 1.2 校验商品分类名称是否重复
        checkName(null, productCategoryBo.getName());
        // 2 核心业务
        return productCategoryRepository.add(productCategoryBo);
        // 3 返回结果
    }

    /**
     * 获取商品分类内容
     *
     * @param queryProductCategoryBo
     * @return
     */
    public ProductCategoryBo get(QueryProductCategoryBo queryProductCategoryBo) {
        // 1 核心校验
        // 2 核心业务
        return productCategoryRepository.get(queryProductCategoryBo);
        // 3 返回结果
    }

    /**
     * 更新商品分类BO
     *
     * @param productCategoryBo
     * @param updateProductCategoryBo
     * @return
     */
    public Integer updateById(ProductCategoryBo productCategoryBo, UpdateProductCategoryBo updateProductCategoryBo) {
        // 1 核心校验
        // 1.1 校验商品分类父编码是否存在
        if (updateProductCategoryBo.getPid() != null) {
            checkPid(updateProductCategoryBo.getPid());
        }
        // 1.2 校验商品分类名称是否已存在
        if (StrUtil.isNotEmpty(updateProductCategoryBo.getName()) && StrUtil.isNotBlank(updateProductCategoryBo.getName().trim())) {
            checkName(productCategoryBo, updateProductCategoryBo.getName());
        }
        // 2 核心业务
        return productCategoryRepository.updateById(productCategoryBo.getId(), updateProductCategoryBo);
        // 3 返回结果
    }

    /**
     * 分页查询商品分类内容
     *
     * @param pageQueryProductCategoryBo
     * @return
     */
    public PageProductCategoryBo page(PageQueryProductCategoryBo pageQueryProductCategoryBo) {
        // 1 核心校验
        // 2 核心业务
        return productCategoryRepository.page(pageQueryProductCategoryBo);
        // 3 返回结果
    }

    private void checkPid(Integer pid) {
        if (!pid.equals(0)) {
            QueryProductCategoryBo queryProductCategoryBo = new QueryProductCategoryBo();
            queryProductCategoryBo.setId(pid);
            ProductCategoryBo contentProductCategoryBo = productCategoryRepository.get(queryProductCategoryBo);
            if (contentProductCategoryBo == null) {
                MallProductServiceException.assertException(HttpStatus.NOT_FOUND, "商品分类父编码非法");
            }
        }
    }

    private void checkName(ProductCategoryBo productCategoryBo, String name) {
        QueryProductCategoryBo queryByName = new QueryProductCategoryBo();
        queryByName.setName(name);
        ProductCategoryBo byName = productCategoryRepository.get(queryByName);
        if (productCategoryBo != null) {
            if (byName != null && !byName.getId().equals(productCategoryBo.getId())) {
                MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品分类名称重复");
            }
        } else {
            if (byName != null) {
                MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品分类名称重复");
            }
        }
    }
}
