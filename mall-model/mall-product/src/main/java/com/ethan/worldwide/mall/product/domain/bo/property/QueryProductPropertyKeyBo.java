package com.ethan.worldwide.mall.product.domain.bo.property;

import lombok.Data;

/**
 * @Author zWX1058539
 * @Description 查询商品规格BO
 * @Date 2022/7/12
 */
@Data
public class QueryProductPropertyKeyBo {

    /**
     * 规格键编码
     */
    private Integer id;

    /**
     * 规格键名称
     */
    private String name;
}
