package com.ethan.worldwide.mall.product.domain.bo.category;

import lombok.Data;

/**
 * @Author zWX1058539
 * @Description 查询商品分类BO
 * @Date 2022/6/29
 */
@Data
public class QueryProductCategoryBo {

    /**
     * 商品分类编码
     */
    private Integer id;

    /**
     * 商品分类名称
     */
    private String name;
}
