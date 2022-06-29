package com.ethan.worldwide.mall.product.domain.bo.brand;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum;
import lombok.Data;

/**
 * @author zhenghui
 * @Description 创建商品品牌BO
 * @Date 2022/6/22
 */
@Data
public class CreateProductBrandBo {


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
