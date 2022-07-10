package com.ethan.worldwide.mall.product.domain.bo.category;

import lombok.Data;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 分页商品分类内容BO
 * @Date 2022/6/30
 */
@Data
public class PageProductCategoryBo {

    private Integer total;

    private List<ProductCategoryBo> data;
}
