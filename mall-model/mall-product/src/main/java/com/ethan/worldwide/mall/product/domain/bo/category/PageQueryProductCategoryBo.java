package com.ethan.worldwide.mall.product.domain.bo.category;

import com.ethan.worldwide.mall.product.infra.dao.enums.DeletedEnum;
import com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * @Author zWX1058539
 * @Description 分页查询商品分类BO
 * @Date 2022/6/30
 */
@Data
public class PageQueryProductCategoryBo {

    private Integer pageNo;

    private Integer pageSize;

    /**
     * 商品分类编码
     */
    private Integer id;

    /**
     * 商品分类父编码
     */
    private Integer pid;

    /**
     * 商品分类名称
     */
    private String name;


    /**
     * 状态：0->关闭;1->开启;
     */
    private StatusEnum status;
}
