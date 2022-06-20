package com.ethan.worldwide.mall.product.infra.dao.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhenghui
 * @Description 是否删除枚举
 * @Date 2022/6/22
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {

    NUMBER_0(0, "已删除"),

    NUMBER_1(1, "未删除");

    @EnumValue
    private Integer value;

    private String message;
}
