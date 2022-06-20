package com.ethan.worldwide.mall.product.domain.bo;

import com.baomidou.mybatisplus.annotation.*;
import com.ethan.worldwide.mall.product.infra.dao.enums.DeletedEnum;
import com.ethan.worldwide.mall.product.infra.dao.enums.StatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * @author zhenghui
 * @Description 商品品牌内容BO
 * @Date 2022/6/22
 */
@Data
public class ContentProductBrandBo {

    /**
     * 品牌编号（主键）
     */
    private Integer id;

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

    /**
     * 是否被删除：0->已删除;1->未删除;
     */
    @EnumValue
    private DeletedEnum deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
