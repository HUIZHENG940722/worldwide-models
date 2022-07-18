package com.ethan.worldwide.mall.product.domain.bo.property;

import com.ethan.worldwide.openapi.interfaces.api.dto.StatusEnum;
import lombok.Data;

/**
 * @Author zWX1058539
 * @Description 分页查询商品规格BO
 * @Date 2022/7/18
 */
@Data
public class PageQueryKeyProductPropertyBo {

    private Integer pageNo;

    private Integer pageSize;

    private String name;

    private StatusEnum status;
}
