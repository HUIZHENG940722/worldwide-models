package com.ethan.worldwide.mall.product.infra.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ethan.worldwide.mall.product.infra.dao.po.brand.ProductBrandPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhenghui
 * @Description 商品品牌数据访问接口
 * @Date 2022/6/22
 */
@Mapper
public interface ProductBrandMapper extends BaseMapper<ProductBrandPo> {
}
