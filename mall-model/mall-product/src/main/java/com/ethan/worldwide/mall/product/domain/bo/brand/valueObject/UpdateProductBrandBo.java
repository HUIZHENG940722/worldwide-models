package com.ethan.worldwide.mall.product.domain.bo.brand.valueObject;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum;
import lombok.Data;

/**
 * @Author zWX1058539
 * @Description 更新商品品牌BO
 * @Date 2022/6/27
 */
@Data
public class UpdateProductBrandBo {

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌描述
     */
    private String description;

    /**
     * 品牌名图片
     */
    private String picUrl;

    /**
     * 状态
     */
    @EnumValue
    private StatusEnum status;
}
