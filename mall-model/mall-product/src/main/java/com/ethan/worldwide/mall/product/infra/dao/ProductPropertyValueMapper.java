package com.ethan.worldwide.mall.product.infra.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.worldwide.mall.product.infra.dao.po.property.ProductPropertyValuePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zWX1058539
 * @Description 商品规格值数据访问接口
 * @Date 2022/7/18
 */
@Mapper
public interface ProductPropertyValueMapper extends BaseMapper<ProductPropertyValuePo> {
}
