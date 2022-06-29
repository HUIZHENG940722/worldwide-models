package com.ethan.worldwide.mall.product.domain.bo.brand;

import lombok.Data;

import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品品牌分页数据BO
 * @Date 2022/6/27
 */
@Data
public class PageProductBrandBo {

    private Integer total;

    private List<ContentProductBrandBo> data;
}
