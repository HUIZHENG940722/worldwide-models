package com.ethan.worldwide.mall.product.domain.bo.brand;

import lombok.Data;

/**
 * @author zhenghui
 * @Description 查询商品品牌BO
 * @Date 2022/6/22
 */
@Data
public class QueryProductBrandBo {

    /**
     * 品牌编号（主键）
     */
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;
}
