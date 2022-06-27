package com.ethan.worldwide.mall.product.application.service;

import com.ethan.worldwide.mall.product.domain.bo.ContentProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.CreateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.PageProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.PageQueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.QueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.UpdateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.service.ProductBrandDomainService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param createProductBrandBo
     * @return
     */
    public Integer createProductBrand(CreateProductBrandBo createProductBrandBo) {
        // 1 校验
        // 2 业务
        // 3 返回结果
        return productBrandDomainService.create(createProductBrandBo);
    }

    /**
     * 获取商品品牌内容
     *
     * @param brandId
     * @return
     */
    public ContentProductBrandBo getProductBrandContent(Integer brandId) {
        // 1 校验
        // 2 业务
        QueryProductBrandBo queryProductBrandBo = new QueryProductBrandBo();
        queryProductBrandBo.setId(brandId);
        // 3 返回结果
        return productBrandDomainService.get(queryProductBrandBo);
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
        // 2 业务
        return productBrandDomainService.updateById(brandId, updateProductBrandBo);
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
}
