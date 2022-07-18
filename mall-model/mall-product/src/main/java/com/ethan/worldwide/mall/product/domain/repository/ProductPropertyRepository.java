package com.ethan.worldwide.mall.product.domain.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ethan.worldwide.mall.product.domain.bo.property.PageQueryKeyProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.PageKeyProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.ProductPropertyKeyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.QueryProductPropertyKeyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.UpdateProductPropertyKeyBo;
import com.ethan.worldwide.mall.product.domain.convert.ProductPropertyPoConvert;
import com.ethan.worldwide.mall.product.infra.dao.ProductPropertyMapper;
import com.ethan.worldwide.mall.product.infra.dao.po.property.ProductPropertyPo;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

/**
 * @Author zWX1058539
 * @Description 商品规格数据仓库
 * @Date 2022/7/12
 */
@Repository
public class ProductPropertyRepository {

    @Autowired
    private ProductPropertyMapper productPropertyMapper;

    public Integer addKey(ProductPropertyKeyBo attrKeyBo) {
        ProductPropertyPo productPropertyPo = ProductPropertyPoConvert.INSTANCE.toPo(attrKeyBo);
        try {
            productPropertyMapper.insert(productPropertyPo);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "添加商品规格数据异常");
        }
        return productPropertyPo.getId();
    }

    public ProductPropertyKeyBo getKey(QueryProductPropertyKeyBo queryProductPropertyKeyBo) {
        LambdaQueryWrapper<ProductPropertyPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (StrUtil.isNotEmpty(queryProductPropertyKeyBo.getName()) && StrUtil.isNotBlank(queryProductPropertyKeyBo.getName().trim())) {
            lambdaQueryWrapper.eq(ProductPropertyPo::getName, queryProductPropertyKeyBo.getName());
        }
        if (queryProductPropertyKeyBo.getId() != null) {
            lambdaQueryWrapper.eq(ProductPropertyPo::getId, queryProductPropertyKeyBo.getId());
        }
        ProductPropertyPo productPropertyPo = productPropertyMapper.selectOne(lambdaQueryWrapper);
        return ProductPropertyPoConvert.INSTANCE.toBo(productPropertyPo);
    }

    public Integer updateKeyById(Integer id, UpdateProductPropertyKeyBo updateProductPropertyKeyBo) {
        ProductPropertyPo productPropertyPo = ProductPropertyPoConvert.INSTANCE.updateToPo(updateProductPropertyKeyBo);
        productPropertyPo.setId(id);
        return productPropertyMapper.updateById(productPropertyPo);
    }

    public PageKeyProductPropertyBo pageKey(PageQueryKeyProductPropertyBo pageQueryKeyProductPropertyBo) {
        LambdaQueryWrapper<ProductPropertyPo> lambdaQueryWrapper = getLambdaQueryWrapper();
        if (StrUtil.isNotEmpty(pageQueryKeyProductPropertyBo.getName()) && StrUtil.isNotBlank(pageQueryKeyProductPropertyBo.getName().trim())) {
            lambdaQueryWrapper.like(ProductPropertyPo::getName, pageQueryKeyProductPropertyBo.getName());
        }
        if (pageQueryKeyProductPropertyBo.getStatus() != null) {
            lambdaQueryWrapper.eq(ProductPropertyPo::getStatus, pageQueryKeyProductPropertyBo.getStatus());
        }
        Page<ProductPropertyPo> objectPage = new Page<>(pageQueryKeyProductPropertyBo.getPageNo(), pageQueryKeyProductPropertyBo.getPageSize());
        Page<ProductPropertyPo> productPropertyPoPage = productPropertyMapper.selectPage(objectPage, lambdaQueryWrapper);
        PageKeyProductPropertyBo pageKeyProductPropertyBo = new PageKeyProductPropertyBo();
        pageKeyProductPropertyBo.setTotal((int) productPropertyPoPage.getTotal());
        pageKeyProductPropertyBo.setData(ProductPropertyPoConvert.INSTANCE.toBo(productPropertyPoPage.getRecords()));
        return pageKeyProductPropertyBo;
    }

    private LambdaQueryWrapper<ProductPropertyPo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(ProductPropertyPo.class);
    }
}
