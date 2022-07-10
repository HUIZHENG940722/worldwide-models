package com.ethan.worldwide.mall.product.application.service;

import com.ethan.worldwide.mall.product.domain.bo.brand.ProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.QueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.PageProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.PageQueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.valueObject.UpdateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.service.ProductBrandDomainService;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author zhenghui
 * @Description 商品品牌应用服务
 * @Date 2022/6/22
 */
@Service
public class ProductBrandService {

    @Autowired
    private ProductBrandDomainService productBrandDomainService;

    /**
     * 创建商品品牌
     *
     * @param productBrandBo
     * @return
     */
    public Integer createProductBrand(ProductBrandBo productBrandBo) {
        // 1 校验
        // 2 业务
        // 3 返回结果
        return productBrandDomainService.create(productBrandBo);
    }

    /**
     * 获取商品品牌内容
     *
     * @param brandId
     * @return
     */
    public ProductBrandBo getProductBrand(Integer brandId) {
        // 1 校验
        // 1.1 校验商品品牌是否存在
        return checkProductBrand(brandId);
        // 2 业务
        // 3 返回结果
    }

    /**
     * 更新商品品牌
     *
     * @param brandId
     * @param updateProductBrandBo
     * @return
     */
    public Integer updateProductById(Integer brandId, UpdateProductBrandBo updateProductBrandBo) {
        // 1 校验
        // 1.1 验证商品实例是否存在
        ProductBrandBo productBrandBo = checkProductBrand(brandId);
        // 2 业务
        return productBrandDomainService.updateById(productBrandBo, updateProductBrandBo);
        // 3 返回结果
    }

    /**
     * 分页获取商品品牌
     *
     * @param pageQueryProductBrandBo
     * @return
     */
    public PageProductBrandBo pageProductBrandContent(PageQueryProductBrandBo pageQueryProductBrandBo) {
        // 1 校验
        // 2 业务
        return productBrandDomainService.page(pageQueryProductBrandBo);
        // 3 返回结果
    }

    private ProductBrandBo checkProductBrand(Integer brandId) {
        QueryProductBrandBo queryProductBrandBo = new QueryProductBrandBo();
        queryProductBrandBo.setId(brandId);
        ProductBrandBo productBrandBo = productBrandDomainService.get(queryProductBrandBo);
        if (productBrandBo == null) {
            MallProductServiceException.assertException(HttpStatus.NOT_FOUND, "获取商品品牌内容失败");
        }
        return productBrandBo;
    }
}
