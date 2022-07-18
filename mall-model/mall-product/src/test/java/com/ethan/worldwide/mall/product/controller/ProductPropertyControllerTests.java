package com.ethan.worldwide.mall.product.controller;

import com.ethan.worldwide.mall.product.MallProductApplicationTests;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductPropertyKeyDto;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductPropertyReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductPropertyValueDto;
import com.ethan.worldwide.openapi.interfaces.api.dto.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zWX1058539
 * @Description 商品规格单元测试
 * @Date 2022/7/18
 */
public class ProductPropertyControllerTests extends MallProductApplicationTests {

    @Test
    public void createProductPropertyTestSuccess() throws Exception {
        post("/mall/product/property", buildCreateProductPropertyReq()).andExpect(MockMvcResultMatchers.status().isCreated());
    }


    private CreateProductPropertyReq buildCreateProductPropertyReq() {
        CreateProductPropertyReq createProductPropertyReq = new CreateProductPropertyReq();
        CreateProductPropertyKeyDto createProductPropertyKeyDto = new CreateProductPropertyKeyDto();
        createProductPropertyKeyDto.setName("颜色");
        createProductPropertyKeyDto.setStatus(StatusEnum.NUMBER_1);
        createProductPropertyReq.setAttrKey(createProductPropertyKeyDto);
        List<CreateProductPropertyValueDto> createProductPropertyValueDtoList = new ArrayList<>();
        CreateProductPropertyValueDto createProductPropertyValueDto1 = new CreateProductPropertyValueDto();
        createProductPropertyValueDto1.setName("红色");
        createProductPropertyValueDto1.setStatus(StatusEnum.NUMBER_1);
        createProductPropertyValueDtoList.add(createProductPropertyValueDto1);
        return createProductPropertyReq;
    }
}
