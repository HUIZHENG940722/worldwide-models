package com.ethan.worldwide.mall.product.controller;

import com.ethan.worldwide.mall.product.MallProductApplicationTests;
import com.ethan.worldwide.openapi.interfaces.api.dto.StatusEnum;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductBrandReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.PageQueryProductBrandReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.UpdateProductBrandReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Author zhenghui
 * @Description 商品品牌单元测试
 * @Date 2022/6/25
 */
public class ProductBrandControllerTests extends MallProductApplicationTests {

    /**
     * 创建商品品牌成功单元测试
     */
    @Test
    public void createProductBrandTestSuccess() throws Exception {
        String contentAsString = post("/mall/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse()
            .getContentAsString();
        Assertions.assertNotNull(contentAsString);
    }

    @Test
    public void createProductBrandTestFail() throws Exception {
        String contentAsString = post("/mall/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse()
            .getContentAsString();
        Assertions.assertNotNull(contentAsString);
        post("/mall/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    private CreateProductBrandReq buildSuccessCreateProductBrandReq() {
        CreateProductBrandReq createProductBrandReq = new CreateProductBrandReq();
        createProductBrandReq.setName("手机");
        createProductBrandReq.setPicUrl("http://dummyimage.com/400x400");
        createProductBrandReq.setDescription("几乎没人一台");
        return createProductBrandReq;
    }

    private CreateProductBrandReq buildSuccessCreateProductBrandReq2() {
        CreateProductBrandReq createProductBrandReq = new CreateProductBrandReq();
        createProductBrandReq.setName("服装");
        createProductBrandReq.setPicUrl("http://dummyimage.com/400x400");
        createProductBrandReq.setDescription("服饰");
        return createProductBrandReq;
    }

    @Test
    public void getProductBrandContentSuccess() throws Exception {
        String contentAsString = post("/mall/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse()
            .getContentAsString();
        Assertions.assertNotNull(contentAsString);
        get("/mall/product/brand/{brand_id}".replace("{brand_id}", contentAsString)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getProductBrandContentFail() throws Exception {
        get("/product/brand/{brand_id}".replace("{brand_id}", "123")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateProductBrandSuccess() throws Exception {
        String contentAsString = post("/mall/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse()
            .getContentAsString();
        Assertions.assertNotNull(contentAsString);
        put("/mall/product/brand/{brand_id}".replace("{brand_id}", contentAsString),
            buildSuccessUpdateProductBrandReq()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateProductBrandFail() throws Exception {
        String contentAsString = post("/mall/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse()
            .getContentAsString();
        post("/mall/product/brand", buildSuccessCreateProductBrandReq2())
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse()
            .getContentAsString();
        put("/mall/product/brand/{brand_id}".replace("{brand_id}", contentAsString),
            buildSuccessUpdateProductBrandReq2()).andExpect(MockMvcResultMatchers.status().isConflict());
    }

    private UpdateProductBrandReq buildSuccessUpdateProductBrandReq() {
        UpdateProductBrandReq updateProductBrandReq = new UpdateProductBrandReq();
        updateProductBrandReq.setName("手机");
        updateProductBrandReq.setDescription("几乎没人一台");
        updateProductBrandReq.picUrl("http://dummyimage.com/400x4003");
        return updateProductBrandReq;
    }

    private UpdateProductBrandReq buildSuccessUpdateProductBrandReq2() {
        UpdateProductBrandReq updateProductBrandReq = new UpdateProductBrandReq();
        updateProductBrandReq.setName("服装");
        updateProductBrandReq.setDescription("几乎没人一台");
        updateProductBrandReq.picUrl("http://dummyimage.com/400x4003");
        return updateProductBrandReq;
    }

    @Test
    public void pageProductBrandContentSuccess() throws Exception {
        post("/mall/product/brand", buildSuccessCreateProductBrandReq())
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse()
            .getContentAsString();
        get("/mall/product/brand/page", buildPageQueryProductBrandReq()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private PageQueryProductBrandReq buildPageQueryProductBrandReq() {
        PageQueryProductBrandReq pageQueryProductBrandReq = new PageQueryProductBrandReq();
        pageQueryProductBrandReq.setPageNo(1);
        pageQueryProductBrandReq.setPageSize(10);
        pageQueryProductBrandReq.setName("手机");
        pageQueryProductBrandReq.setStatus(StatusEnum.NUMBER_1);
        return pageQueryProductBrandReq;
    }
}
