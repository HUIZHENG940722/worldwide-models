package com.ethan.worldwide.mall.product.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethan.worldwide.mall.product.domain.bo.category.PageProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.ProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.valueObject.UpdateProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.convert.ProductCategoryPoConvert;
import com.ethan.worldwide.mall.product.infra.dao.po.category.ProductCategoryPo;
import com.ethan.worldwide.mall.product.domain.bo.category.PageQueryProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.QueryProductCategoryBo;
import com.ethan.worldwide.mall.product.infra.dao.ProductCategoryMapper;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

/**
 * @Author zWX1058539
 * @Description 商品分类数据仓库
 * @Date 2022/6/29
 */
@Repository
public class ProductCategoryRepository {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public Integer add(ProductCategoryBo productCategoryBo) {
        ProductCategoryPo productCategoryPo = ProductCategoryPoConvert.INSTANCE.toPo(productCategoryBo);
        try {
            productCategoryMapper.insert(productCategoryPo);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "插入数据异常");
        }
        return productCategoryPo.getId();
    }


    public ProductCategoryBo get(QueryProductCategoryBo queryProductCategoryBo) {
        LambdaQueryWrapper<ProductCategoryPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (queryProductCategoryBo.getId() != null) {
            lambdaQueryWrapper.eq(ProductCategoryPo::getId, queryProductCategoryBo.getId());
        }
        if (StrUtil.isNotEmpty(queryProductCategoryBo.getName()) && StrUtil.isNotBlank(queryProductCategoryBo.getName().trim())) {
            lambdaQueryWrapper.eq(ProductCategoryPo::getName, queryProductCategoryBo.getName());
        }
        ProductCategoryPo productCategoryPo = null;
        try {
            productCategoryPo = productCategoryMapper.selectOne(lambdaQueryWrapper);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "商品分类查询异常");
        }

        return ProductCategoryPoConvert.INSTANCE.toBo(productCategoryPo);
    }

    public Integer updateById(Integer id, UpdateProductCategoryBo updateProductCategoryBo) {
        ProductCategoryPo productCategoryPo = ProductCategoryPoConvert.INSTANCE.updateBoToPo(updateProductCategoryBo);
        productCategoryPo.setId(id);
        Integer update = null;
        try {
            update = productCategoryMapper.updateById(productCategoryPo);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "更新商品分类异常");
        }
        return productCategoryMapper.updateById(productCategoryPo);
    }

    public PageProductCategoryBo page(PageQueryProductCategoryBo pageQueryProductCategoryBo) {
        LambdaQueryWrapper<ProductCategoryPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (pageQueryProductCategoryBo.getId() != null) {
            lambdaQueryWrapper.eq(ProductCategoryPo::getId, pageQueryProductCategoryBo.getId());
        }
        if (pageQueryProductCategoryBo.getPid() != null) {
            lambdaQueryWrapper.eq(ProductCategoryPo::getPid, pageQueryProductCategoryBo.getPid());
        }
        if (StrUtil.isNotEmpty(pageQueryProductCategoryBo.getName()) && StrUtil.isNotBlank(pageQueryProductCategoryBo.getName())) {
            lambdaQueryWrapper.like(ProductCategoryPo::getName, pageQueryProductCategoryBo.getName());
        }
        if (pageQueryProductCategoryBo.getStatus() != null) {
            lambdaQueryWrapper.eq(ProductCategoryPo::getStatus, pageQueryProductCategoryBo.getStatus());
        }
        Page<ProductCategoryPo> objectPage = new Page<>(pageQueryProductCategoryBo.getPageNo(), pageQueryProductCategoryBo.getPageSize());
        Page<ProductCategoryPo> productCategoryPoPage = null;
        try {
            productCategoryPoPage = productCategoryMapper.selectPage(objectPage, lambdaQueryWrapper);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "查询商品分类异常");
        }
        PageProductCategoryBo pageProductCategoryBo = new PageProductCategoryBo();
        pageProductCategoryBo.setTotal((int) productCategoryPoPage.getTotal());
        pageProductCategoryBo.setData(ProductCategoryPoConvert.INSTANCE.toBo(productCategoryPoPage.getRecords()));
        return pageProductCategoryBo;
    }

    private LambdaQueryWrapper<ProductCategoryPo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(ProductCategoryPo.class);
    }
}
