package com.ethan.worldwide.mall.product.infra.dao.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhenghui
 * @Description 状态枚举
 * @Date 2022/6/22
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    NUMBER_0(0, "关闭"),

    NUMBER_1(1, "开启");

    @EnumValue
    private Integer value;

    private String message;
}
