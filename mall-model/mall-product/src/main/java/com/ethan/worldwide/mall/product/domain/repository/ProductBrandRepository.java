package com.ethan.worldwide.mall.product.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethan.worldwide.mall.product.domain.bo.brand.*;
import com.ethan.worldwide.mall.product.domain.bo.brand.valueObject.UpdateProductBrandBo;
import com.ethan.worldwide.mall.product.infra.dao.ProductBrandMapper;
import com.ethan.worldwide.mall.product.domain.convert.ProductBrandPoConvert;
import com.ethan.worldwide.mall.product.infra.dao.po.brand.ProductBrandPo;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

/**
 * @author zhenghui
 * @Description 商品品牌数据仓库
 * @Date 2022/6/22
 */
@Repository
public class ProductBrandRepository {

    @Autowired
    private ProductBrandMapper productBrandMapper;

    public Integer add(ProductBrandBo productBrandBo) {
        ProductBrandPo boToPo = ProductBrandPoConvert.INSTANCE.toPo(productBrandBo);
        try {
            productBrandMapper.insert(boToPo);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "插入商品品牌异常");
        }
        return boToPo.getId();
    }

    public ProductBrandBo get(QueryProductBrandBo queryProductBrandBo) {
        LambdaQueryWrapper<ProductBrandPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (queryProductBrandBo.getId() != null) {
            lambdaQueryWrapper.eq(ProductBrandPo::getId, queryProductBrandBo.getId());
        }
        if (StrUtil.isNotEmpty(queryProductBrandBo.getName())) {
            lambdaQueryWrapper.eq(ProductBrandPo::getName, queryProductBrandBo.getName());
        }
        ProductBrandPo productBrandPo = null;
        try {
            productBrandPo = productBrandMapper.selectOne(lambdaQueryWrapper);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "查询商品品牌异常");
        }
        return productBrandPo != null ? ProductBrandPoConvert.INSTANCE.toBo(productBrandPo) : null;
    }

    public Integer updateById(Integer id, UpdateProductBrandBo updateProductBrandBo) {
        ProductBrandPo productBrandPo = ProductBrandPoConvert.INSTANCE.updateBoToPo(updateProductBrandBo);
        productBrandPo.setId(id);
        Integer update = null;
        try {
            update = productBrandMapper.updateById(productBrandPo);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "更新商品品牌异常");
        }
        return update;
    }

    public PageProductBrandBo page(PageQueryProductBrandBo pageQueryProductBrandBo) {
        LambdaQueryWrapper<ProductBrandPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (pageQueryProductBrandBo.getId() != null) {
            lambdaQueryWrapper.eq(ProductBrandPo::getId, pageQueryProductBrandBo.getId());
        }
        if (pageQueryProductBrandBo.getStatus() != null) {
            lambdaQueryWrapper.eq(ProductBrandPo::getStatus, pageQueryProductBrandBo.getStatus());
        }
        if (StrUtil.isNotEmpty(pageQueryProductBrandBo.getName()) && StrUtil.isNotBlank(pageQueryProductBrandBo.getName().trim())) {
            lambdaQueryWrapper.eq(ProductBrandPo::getName, pageQueryProductBrandBo.getName());
        }
        Page<ProductBrandPo> objectPage = new Page<>(pageQueryProductBrandBo.getPageNo(), pageQueryProductBrandBo.getPageSize());
        Page<ProductBrandPo> productBrandPoPage = productBrandMapper.selectPage(objectPage, lambdaQueryWrapper);
        PageProductBrandBo pageProductBrandBo = new PageProductBrandBo();
        pageProductBrandBo.setTotal(pageProductBrandBo.getTotal());
        pageProductBrandBo.setData(ProductBrandPoConvert.INSTANCE.toBo(productBrandPoPage.getRecords()));
        return pageProductBrandBo;
    }

    private LambdaQueryWrapper<ProductBrandPo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(ProductBrandPo.class);
    }
}
