package com.ethan.worldwide.mall.product.domain.bo.category;

import com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum;
import lombok.Data;

/**
 * @Author zWX1058539
 * @Description 创建商品分类BO
 * @Date 2022/6/29
 */
@Data
public class CreateProductCategoryBo {

    /**
     * 商品分类父编码
     */
    private Integer pid;

    /**
     * 商品分类名称
     */
    private String name;

    /**
     * 商品分类描述
     */
    private String description;

    /**
     * 商品分类图片
     */
    private String picUrl;

    /**
     * 商品分类排序
     */
    private Integer sort;

    /**
     * 状态：0->关闭;1->开启;
     */
    private StatusEnum status;
}
