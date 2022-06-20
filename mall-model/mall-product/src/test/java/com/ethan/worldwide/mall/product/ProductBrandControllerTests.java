package com.ethan.worldwide.mall.product;

import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductBrandReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Author zhenghui
 * @Description 商品品牌单元测试
 * @Date 2022/6/25
 */
public class ProductBrandControllerTests extends MallProductApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * 创建商品品牌成功单元测试
     */
    @Test
    public void createProductBrandTestSuccess() throws Exception {
        String contentAsString = post("/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse()
            .getContentAsString();
        Assertions.assertNotNull(contentAsString);
    }

    @Test
    public void createProductBrandTestFail() throws Exception {
        String contentAsString = post("/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse()
            .getContentAsString();
        Assertions.assertNotNull(contentAsString);
        post("/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    private CreateProductBrandReq buildSuccessCreateProductBrandReq() {
        CreateProductBrandReq createProductBrandReq = new CreateProductBrandReq();
        createProductBrandReq.setName("测试2");
        createProductBrandReq.setPicUrl("http://dummyimage.com/400x400");
        createProductBrandReq.setDescription("几乎没人一台");
        return createProductBrandReq;
    }
}
