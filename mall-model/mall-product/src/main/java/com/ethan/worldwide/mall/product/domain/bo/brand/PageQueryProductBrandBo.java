package com.ethan.worldwide.mall.product.domain.bo.brand;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum;
import lombok.Data;

/**
 * @Author zWX1058539
 * @Description 分页查询商品品牌Bo
 * @Date 2022/6/27
 */
@Data
public class PageQueryProductBrandBo {

    private Integer pageNo;

    private Integer pageSize;

    /**
     * 品牌编号（主键）
     */
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 状态
     */
    @EnumValue
    private StatusEnum status;
}
