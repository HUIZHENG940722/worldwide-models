package com.ethan.worldwide.mall.product.domain.service;

import com.ethan.worldwide.mall.product.domain.bo.property.PageProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.PageQueryKeyProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.ProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.QueryProductPropertyKeyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.PageKeyProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.ProductPropertyKeyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.UpdateProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.ProductPropertyValueBo;
import com.ethan.worldwide.mall.product.domain.repository.ProductPropertyRepository;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品规格领域服务
 * @Date 2022/7/12
 */
@Service
public class ProductPropertyDomainService {

    @Autowired
    private ProductPropertyRepository productPropertyRepository;

    @Autowired
    private ProductPropertyValueDomainService productPropertyValueDomainService;

    /**
     * 添加商品规格
     *
     * @param productPropertyBo
     * @return
     */
    @Transactional
    public Integer add(ProductPropertyBo productPropertyBo) {
        // 1 核心校验
        // 1.1 校验商品规格名称是否重复
        checkName(null, productPropertyBo.getPropertyKey().getName());
        // 2 核心业务
        // 2.1 添加商品规格
        Integer propertyId = productPropertyRepository.addKey(productPropertyBo.getPropertyKey());
        // 2.2 添加商品规格值列表
        productPropertyValueDomainService.addListByPropertyId(propertyId, productPropertyBo.getPropertyValueList());
        // 3 返回结果
        return propertyId;
    }

    /**
     * 获取商品规格内容
     *
     * @param queryProductPropertyKeyBo
     * @return
     */
    public ProductPropertyBo get(QueryProductPropertyKeyBo queryProductPropertyKeyBo) {
        // 1 核心校验
        // 2 核心业务
        // 2.1 获取商品规格键
        ProductPropertyKeyBo key = productPropertyRepository.getKey(queryProductPropertyKeyBo);
        // 2.2 获取商品规格值列表
        List<ProductPropertyValueBo> propertyValueBoList = productPropertyValueDomainService.getByPropertyId(key.getId());
        // 3 返回结果
        ProductPropertyBo productPropertyBo = new ProductPropertyBo();
        productPropertyBo.setPropertyKey(key);
        productPropertyBo.setPropertyValueList(propertyValueBoList);
        return productPropertyBo;
    }

    public Integer update(ProductPropertyBo productPropertyBo, UpdateProductPropertyBo updateProductPropertyBo) {
        // 1 核心校验
        // 1.1 校验商品规格键名称是否重复
        checkName(productPropertyBo.getPropertyKey(), updateProductPropertyBo.getPropertyKey().getName());
        // 2 核心业务
        // 2.1 更新商品规格键
        Integer update = productPropertyRepository.updateKeyById(productPropertyBo.getPropertyKey().getId(), updateProductPropertyBo.getPropertyKey());
        // 2.2 更新商品规格值
        List<ProductPropertyValueBo> byPropertyId = productPropertyValueDomainService.getByPropertyId(productPropertyBo.getPropertyKey().getId());
        productPropertyValueDomainService.updateByProperty(byPropertyId, updateProductPropertyBo.getProductPropertyValueList());
        // 3 返回结果
        return update;
    }


    public PageProductPropertyBo page(PageQueryKeyProductPropertyBo pageQueryKeyProductPropertyBo) {
        // 1 核心校验
        // 2 核心业务
        // 2.1 获取商品规格键分页数据
        PageKeyProductPropertyBo pageKeyProductPropertyBo = productPropertyRepository.pageKey(pageQueryKeyProductPropertyBo);
        PageProductPropertyBo pageProductPropertyBo = new PageProductPropertyBo();
        pageProductPropertyBo.setTotal(pageKeyProductPropertyBo.getTotal());
        List<ProductPropertyKeyBo> data = pageKeyProductPropertyBo.getData();
        List<ProductPropertyBo> productPropertyBoData = pageProductPropertyBo.getData();
        for (ProductPropertyKeyBo datum : data) {
            // 2.2 获取商品规格值列表
            List<ProductPropertyValueBo> byPropertyId = productPropertyValueDomainService.getByPropertyId(datum.getId());
            ProductPropertyBo productPropertyBo = new ProductPropertyBo();
            productPropertyBo.setPropertyKey(datum);
            productPropertyBo.setPropertyValueList(byPropertyId);
            productPropertyBoData.add(productPropertyBo);
        }
        // 3 返回结果
        pageProductPropertyBo.setData(productPropertyBoData);
        return pageProductPropertyBo;
    }

    private void checkName(ProductPropertyKeyBo productPropertyKeyBo, String name) {
        QueryProductPropertyKeyBo queryByName = new QueryProductPropertyKeyBo();
        queryByName.setName(name);
        ProductPropertyKeyBo byId = productPropertyRepository.getKey(queryByName);
        if (productPropertyKeyBo != null) {
            if (byId != null && !byId.getId().equals(productPropertyKeyBo.getId())) {
                MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品规格键名称重复");
            }
        } else {
            if (byId != null) {
                MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品规格键名称重复");
            }
        }
    }
}
