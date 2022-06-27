package com.ethan.worldwide.mall.product.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ethan.worldwide.mall.product.domain.bo.ContentProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.CreateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.QueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.UpdateProductBrandBo;
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

    private LambdaQueryWrapper<ProductBrandPo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(ProductBrandPo.class);
    }
}
