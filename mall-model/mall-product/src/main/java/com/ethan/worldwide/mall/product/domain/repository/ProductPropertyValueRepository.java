package com.ethan.worldwide.mall.product.domain.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.ProductPropertyValueBo;
import com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.valueObject.UpdateProductPropertyValueBo;
import com.ethan.worldwide.mall.product.domain.convert.ProductPropertyValuePoConvert;
import com.ethan.worldwide.mall.product.infra.dao.ProductPropertyValueMapper;
import com.ethan.worldwide.mall.product.infra.dao.po.property.ProductPropertyValuePo;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品规格值数据仓库
 * @Date 2022/7/18
 */
@Repository
public class ProductPropertyValueRepository {

    @Autowired
    private ProductPropertyValueMapper productPropertyValueMapper;

    public Integer add(ProductPropertyValueBo productPropertyValueBo) {
        ProductPropertyValuePo productPropertyValuePo = ProductPropertyValuePoConvert.INSTANCE.toPo(productPropertyValueBo);
        try {
            productPropertyValueMapper.insert(productPropertyValuePo);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "插入商品规格值数据库异常");
        }

        return productPropertyValuePo.getId();
    }

    public Integer addList(List<ProductPropertyValueBo> propertyValueBoList) {
        List<ProductPropertyValuePo> propertyValuePoList = ProductPropertyValuePoConvert.INSTANCE.toPo(propertyValueBoList);
        for (ProductPropertyValuePo productPropertyValuePo : propertyValuePoList) {
            try {
                productPropertyValueMapper.insert(productPropertyValuePo);
            } catch (MallProductServiceException e) {
                MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "插入商品规格值数据库异常");
            }
        }
        return propertyValueBoList.size();
    }

    public List<ProductPropertyValueBo> listByPropertyId(Integer id) {
        LambdaQueryWrapper<ProductPropertyValuePo> lambdaQueryWrapper = getLambdaQueryWrapper();
        lambdaQueryWrapper.eq(ProductPropertyValuePo::getPropertyId, id);
        List<ProductPropertyValuePo> propertyValuePoList = null;
        try {
            propertyValuePoList = productPropertyValueMapper.selectList(lambdaQueryWrapper);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "获取商品规格值异常");
        }

        return ProductPropertyValuePoConvert.INSTANCE.toBo(propertyValuePoList);
    }

    public Integer updateById(UpdateProductPropertyValueBo updateProductPropertyValueBo) {
        ProductPropertyValuePo productPropertyValuePo = ProductPropertyValuePoConvert.INSTANCE.updateToPo(updateProductPropertyValueBo);
        try {
            return productPropertyValueMapper.updateById(productPropertyValuePo);
        } catch (MallProductServiceException e) {
            MallProductServiceException.assertException(HttpStatus.INTERNAL_SERVER_ERROR, "更新商品规格值异常");
        }
        return null;
    }

    private LambdaQueryWrapper<ProductPropertyValuePo> getLambdaQueryWrapper() {
        return Wrappers.lambdaQuery(ProductPropertyValuePo.class);
    }
}
