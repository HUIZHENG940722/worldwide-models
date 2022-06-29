package com.ethan.worldwide.mall.product.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethan.worldwide.mall.product.domain.bo.brand.ContentProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.CreateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.PageProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.PageQueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.QueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.UpdateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.convert.ProductBrandPoConvert;
import com.ethan.worldwide.mall.product.infra.dao.ProductBrandMapper;
import com.ethan.worldwide.mall.product.infra.dao.po.brand.ProductBrandPo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Integer add(CreateProductBrandBo createProductBrandBo) {
        ProductBrandPo boToPo = ProductBrandPoConvert.INSTANCE.createBoToPo(createProductBrandBo);
        productBrandMapper.insert(boToPo);
        return boToPo.getId();
    }

    public ContentProductBrandBo get(QueryProductBrandBo queryProductBrandBo) {
        LambdaQueryWrapper<ProductBrandPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (queryProductBrandBo.getId() != null) {
            lambdaQueryWrapper.eq(ProductBrandPo::getId, queryProductBrandBo.getId());
        }
        if (StrUtil.isNotEmpty(queryProductBrandBo.getName())) {
            lambdaQueryWrapper.eq(ProductBrandPo::getName, queryProductBrandBo.getName());
        }
        ProductBrandPo productBrandPo = productBrandMapper.selectOne(lambdaQueryWrapper);
        return productBrandPo != null ? ProductBrandPoConvert.INSTANCE.toContentBo(productBrandPo) : null;
    }

    public Integer updateById(Integer id, UpdateProductBrandBo updateProductBrandBo) {
        ProductBrandPo productBrandPo = ProductBrandPoConvert.INSTANCE.updateBoToPo(updateProductBrandBo);
        productBrandPo.setId(id);
        return productBrandMapper.updateById(productBrandPo);
    }

    public PageProductBrandBo page(PageQueryProductBrandBo pageQueryProductBrandBo) {
        LambdaQueryWrapper<ProductBrandPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (pageQueryProductBrandBo.getId()!=null) {
            lambdaQueryWrapper.eq(ProductBrandPo::getId, pageQueryProductBrandBo.getId());
        }
        if (pageQueryProductBrandBo.getStatus()!=null) {
            lambdaQueryWrapper.eq(ProductBrandPo::getStatus, pageQueryProductBrandBo.getStatus());
        }
        if (StrUtil.isNotEmpty(pageQueryProductBrandBo.getName()) && StrUtil.isNotBlank(pageQueryProductBrandBo.getName().trim())) {
            lambdaQueryWrapper.eq(ProductBrandPo::getName, pageQueryProductBrandBo.getName());
        }
        Page<ProductBrandPo> objectPage = new Page<>(pageQueryProductBrandBo.getPageNo(), pageQueryProductBrandBo.getPageSize());
        Page<ProductBrandPo> productBrandPoPage = productBrandMapper.selectPage(objectPage, lambdaQueryWrapper);
        PageProductBrandBo pageProductBrandBo = new PageProductBrandBo();
        pageProductBrandBo.setTotal(pageProductBrandBo.getTotal());
        pageProductBrandBo.setData(ProductBrandPoConvert.INSTANCE.toContentBo(productBrandPoPage.getRecords()));
        return pageProductBrandBo;
    }

    private LambdaQueryWrapper<ProductBrandPo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(ProductBrandPo.class);
    }
}
