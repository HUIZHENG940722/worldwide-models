package com.ethan.worldwide.mall.product.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethan.worldwide.mall.product.domain.bo.category.ContentProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.CreateProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.PageProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.PageQueryProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.QueryProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.bo.category.UpdateProductCategoryBo;
import com.ethan.worldwide.mall.product.domain.convert.ProductCategoryPoConvert;
import com.ethan.worldwide.mall.product.infra.dao.ProductCategoryMapper;
import com.ethan.worldwide.mall.product.infra.dao.po.category.ProductCategoryPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品分类数据仓库
 * @Date 2022/6/29
 */
@Repository
public class ProductCategoryRepository {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public Integer add(CreateProductCategoryBo createProductCategoryBo) {
        ProductCategoryPo productCategoryPo = ProductCategoryPoConvert.INSTANCE.createBoToPo(createProductCategoryBo);
        productCategoryMapper.insert(productCategoryPo);
        return productCategoryPo.getId();
    }


    public ContentProductCategoryBo get(QueryProductCategoryBo queryProductCategoryBo) {
        LambdaQueryWrapper<ProductCategoryPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (queryProductCategoryBo.getId() != null) {
            lambdaQueryWrapper.eq(ProductCategoryPo::getId, queryProductCategoryBo.getId());
        }
        if (StrUtil.isNotEmpty(queryProductCategoryBo.getName()) && StrUtil.isNotBlank(queryProductCategoryBo.getName().trim())) {
            lambdaQueryWrapper.eq(ProductCategoryPo::getName, queryProductCategoryBo.getName());
        }
        return ProductCategoryPoConvert.INSTANCE.toContentBo(productCategoryMapper.selectOne(lambdaQueryWrapper));
    }

    public Integer updateById(Integer id, UpdateProductCategoryBo updateProductCategoryBo) {
        ProductCategoryPo productCategoryPo = ProductCategoryPoConvert.INSTANCE.updateBoToPo(updateProductCategoryBo);
        productCategoryPo.setId(id);
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
        Page<ProductCategoryPo> productCategoryPoPage = productCategoryMapper.selectPage(objectPage, lambdaQueryWrapper);
        PageProductCategoryBo pageProductCategoryBo = new PageProductCategoryBo();
        pageProductCategoryBo.setTotal((int) productCategoryPoPage.getTotal());
        pageProductCategoryBo.setData(ProductCategoryPoConvert.INSTANCE.toPageBo(productCategoryPoPage.getRecords()));
        return pageProductCategoryBo;
    }

    private LambdaQueryWrapper<ProductCategoryPo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(ProductCategoryPo.class);
    }
}
