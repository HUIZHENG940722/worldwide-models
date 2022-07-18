package com.ethan.worldwide.mall.product.domain.service;

import cn.hutool.core.bean.BeanUtil;
import com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.ProductPropertyValueBo;
import com.ethan.worldwide.mall.product.domain.bo.property.propertyvalue.valueObject.UpdateProductPropertyValueBo;
import com.ethan.worldwide.mall.product.domain.repository.ProductPropertyValueRepository;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品规格值领域服务
 * @Date 2022/7/18
 */
@Service
public class ProductPropertyValueDomainService {

    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;


    @Transactional
    public Integer addListByPropertyId(Integer propertyId, List<ProductPropertyValueBo> propertyValueBoList) {
        // 1 核心校验
        // 2 核心业务
        // 2.1 初始化规格编码
        for (ProductPropertyValueBo productPropertyValueBo : propertyValueBoList) {
            productPropertyValueBo.setPropertyId(propertyId);
        }
        // 3 返回结果
        return productPropertyValueRepository.addList(propertyValueBoList);
    }

    public Integer add(ProductPropertyValueBo productPropertyValueBo) {
        return productPropertyValueRepository.add(productPropertyValueBo);
    }

    public List<ProductPropertyValueBo> getByPropertyId(Integer id) {
        return productPropertyValueRepository.listByPropertyId(id);
    }

    @Transactional
    public Integer updateByProperty(List<ProductPropertyValueBo> productPropertyValueList,
        List<UpdateProductPropertyValueBo> updateProductPropertyValueList) {
        // 1 核心校验
        // 2 核心业务
        // 2.1 之前存在的更新字段值
        // 2.2 之前不存在的插入数据
        // 2.3 已经不存在的删除数据
        for (UpdateProductPropertyValueBo updateProductPropertyValueBo : updateProductPropertyValueList) {
            if (updateProductPropertyValueBo.getId() != null) {
                // 找出对应的规格值BO
                ProductPropertyValueBo productPropertyValueBo = findUpdatedProductPropertyValue(productPropertyValueList, updateProductPropertyValueBo.getId());
                if (productPropertyValueBo == null) {
                    MallProductServiceException.assertException(HttpStatus.BAD_REQUEST, "无效的商品规格值");
                }
                update(productPropertyValueBo, updateProductPropertyValueBo);
            } else {
                ProductPropertyValueBo productPropertyValueBo = BeanUtil.toBean(updateProductPropertyValueBo, ProductPropertyValueBo.class);
                add(productPropertyValueBo);
            }
            // 剩下的propertyValueBoList删除
        }
        // 3 返回结果
        return updateProductPropertyValueList.size() + productPropertyValueList.size();
    }

    public Integer update(ProductPropertyValueBo productPropertyValueBo, UpdateProductPropertyValueBo updateProductPropertyValueBo) {
        // 1 核心校验
        // 2 核心业务
        return productPropertyValueRepository.updateById(updateProductPropertyValueBo);
        // 3 返回结果
    }

    private ProductPropertyValueBo findUpdatedProductPropertyValue(List<ProductPropertyValueBo> propertyValueBoList, Integer propertyValueId) {
        for (int i = 0; i < propertyValueBoList.size(); i++) {
            if (propertyValueBoList.get(i).getId().equals(propertyValueId)) {
                ProductPropertyValueBo result = propertyValueBoList.get(i);
                propertyValueBoList.remove(i);
                return result;
            }
        }
        return null;
    }
}
