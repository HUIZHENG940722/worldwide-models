package com.ethan.worldwide.mall.product.infra.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.worldwide.mall.product.infra.dao.po.category.ProductCategoryPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zWX1058539
 * @Description 商品分类数据访问层
 * @Date 2022/6/29
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategoryPo> {
}
