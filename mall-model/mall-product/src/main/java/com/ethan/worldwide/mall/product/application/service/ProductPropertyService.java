package com.ethan.worldwide.mall.product.application.service;

import com.ethan.worldwide.mall.product.domain.bo.property.PageProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.PageQueryKeyProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.ProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.QueryProductPropertyKeyBo;
import com.ethan.worldwide.mall.product.domain.bo.property.valueObject.UpdateProductPropertyBo;
import com.ethan.worldwide.mall.product.domain.service.ProductPropertyDomainService;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @Author zWX1058539
 * @Description 商品规格应用服务
 * @Date 2022/7/12
 */
@Service
public class ProductPropertyService {

    @Autowired
    private ProductPropertyDomainService productPropertyDomainService;

    public Integer createProductProperty(ProductPropertyBo productPropertyBo) {
        // 1 校验
        // 2 业务
        return productPropertyDomainService.add(productPropertyBo);
        // 3 返回结果
    }

    public ProductPropertyBo getProductProperty(Integer propertyKeyId) {
        // 1 校验
        QueryProductPropertyKeyBo queryById = new QueryProductPropertyKeyBo();
        queryById.setId(propertyKeyId);
        return checkProductProperty(queryById);
        // 2 业务
        // 3 返回结果
    }

    public Integer updateProductProperty(Integer propertyKeyId, UpdateProductPropertyBo updateProductPropertyBo) {
        // 1 校验
        QueryProductPropertyKeyBo queryById = new QueryProductPropertyKeyBo();
        queryById.setId(propertyKeyId);
        ProductPropertyBo productPropertyBo = checkProductProperty(queryById);
        // 2 业务
        // 3 返回结果
        return productPropertyDomainService.update(productPropertyBo, updateProductPropertyBo);
    }


    public PageProductPropertyBo pageProductProperty(PageQueryKeyProductPropertyBo pageQueryKeyProductPropertyBo) {
        // 2 业务
        // 3 返回结果
        // 1 校验
        return productPropertyDomainService.page(pageQueryKeyProductPropertyBo);
    }

    /**
     * 校验商品规格是否存在
     *
     * @param queryProductPropertyKeyBo
     * @return
     */
    private ProductPropertyBo checkProductProperty(QueryProductPropertyKeyBo queryProductPropertyKeyBo) {
        ProductPropertyBo productPropertyBo = productPropertyDomainService.get(queryProductPropertyKeyBo);
        if (productPropertyBo == null) {
            MallProductServiceException.assertException(HttpStatus.NOT_FOUND, "商品规格无效");
        }
        return productPropertyBo;
    }
}
