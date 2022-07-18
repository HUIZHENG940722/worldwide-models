package com.ethan.worldwide.mall.product.infra.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.worldwide.mall.product.infra.dao.po.property.ProductPropertyPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zWX1058539
 * @Description 商品属性键数据访问接口
 * @Date 2022/7/12
 */
@Mapper
public interface ProductPropertyMapper extends BaseMapper<ProductPropertyPo> {
}
