package com.ethan.worldwide.mall.product.domain.bo.property;

import lombok.Data;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品规格分页BO
 * @Date 2022/7/18
 */
@Data
public class PageProductPropertyBo {

    private Integer total;

    private List<ProductPropertyBo> data;
}
