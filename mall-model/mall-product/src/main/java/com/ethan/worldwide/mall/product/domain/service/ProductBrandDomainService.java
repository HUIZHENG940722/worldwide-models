package com.ethan.worldwide.mall.product.domain.service;

import com.ethan.worldwide.mall.product.domain.bo.brand.ContentProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.CreateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.QueryProductBrandBo;
import com.ethan.worldwide.mall.product.infra.exception.MallProductServiceException;
import com.ethan.worldwide.mall.product.domain.bo.brand.PageProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.PageQueryProductBrandBo;
import com.ethan.worldwide.mall.product.domain.bo.brand.UpdateProductBrandBo;
import com.ethan.worldwide.mall.product.domain.repository.ProductBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhenghui
 * @Description 商品品牌领域服务
 * @Date 2022/6/22
 */
@Service
public class ProductBrandDomainService {

    @Autowired
    private ProductBrandRepository productBrandRepository;

    /**
     * 创建商品品牌
     *
     * @param createProductBrandBo
     * @return
     */
    @Transactional
    public Integer create(CreateProductBrandBo createProductBrandBo) {
        // 1 核心校验
        // 1.1 验证商品品牌名称是否重复
        QueryProductBrandBo queryProductBrandBo = new QueryProductBrandBo();
        queryProductBrandBo.setName(createProductBrandBo.getName());
        ContentProductBrandBo contentProductBrandBo = get(queryProductBrandBo);
        if (contentProductBrandBo != null) {
            MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品品牌名称重复");
        }
        // 2 核心业务
        // 2.1 插入数据
        return productBrandRepository.add(createProductBrandBo);
        // 3 返回结果
    }

    /**
     * 获取商品品牌内容
     *
     * @param queryProductBrandBo
     * @return
     */
    public ContentProductBrandBo get(QueryProductBrandBo queryProductBrandBo) {
        // 1 核心校验
        // 2 核心业务
        return productBrandRepository.get(queryProductBrandBo);
        // 3 返回结果
    }

    /**
     * 更新商品品牌内容
     *
     * @param id
     * @param updateProductBrandBo
     * @return
     */
    @Transactional
    public Integer updateById(Integer id, UpdateProductBrandBo updateProductBrandBo) {
        // 1 核心校验
        // 1.1 校验品牌是否存在
        QueryProductBrandBo queryById = new QueryProductBrandBo();
        queryById.setId(id);
        ContentProductBrandBo byId = productBrandRepository.get(queryById);
        if (byId == null) {
            MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品品牌不存在");
        }
        // 1.2 校验商品品牌名称是否重复
        if (updateProductBrandBo.getName() != null) {
            QueryProductBrandBo queryByName = new QueryProductBrandBo();
            queryByName.setName(updateProductBrandBo.getName());
            ContentProductBrandBo byName = productBrandRepository.get(queryByName);
            if (byName != null && !byName.getId().equals(id)) {
                MallProductServiceException.assertException(HttpStatus.CONFLICT, "商品品牌名称重复");
            }
        }
        // 2 核心业务
        return productBrandRepository.updateById(id, updateProductBrandBo);
        // 3 返回结果
    }


    /**
     * 分页获取商品品牌内容
     *
     * @param pageQueryProductBrandBo
     * @return
     */
    public PageProductBrandBo page(PageQueryProductBrandBo pageQueryProductBrandBo) {
        // 1 核心校验
        // 2 核心业务
        return productBrandRepository.page(pageQueryProductBrandBo);
        // 3 返回结果
    }
}
