package com.ethan.worldwide.mall.product.controller;

import com.ethan.worldwide.mall.product.MallProductApplicationTests;
import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductCategoryReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.PageQueryProductCategoryReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.UpdateProductCategoryReq;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Author zWX1058539
 * @Description 商品分类单元测试
 * @Date 2022/6/29
 */
public class ProductCategoryControllerTests extends MallProductApplicationTests {

    @Test
    public void createProductCategoryTestSuccess() throws Exception {
        post("/mall/product/category", buildSuccessCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createProductCategoryTestFail() throws Exception {
        post("/mall/product/category", buildFailCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private CreateProductCategoryReq buildSuccessCreateProductCategoryReq() {
        CreateProductCategoryReq createProductCategoryReq = new CreateProductCategoryReq();
        createProductCategoryReq.setName("手机");
        createProductCategoryReq.setDescription("手机分类产品");
        createProductCategoryReq.setPicUrl("http://dummyimage.com/400x400");
        createProductCategoryReq.setSort(0);
        createProductCategoryReq.setPid(0);
        return createProductCategoryReq;
    }

    private CreateProductCategoryReq buildSuccessCreateProductCategoryReq2() {
        CreateProductCategoryReq createProductCategoryReq = new CreateProductCategoryReq();
        createProductCategoryReq.setName("服装");
        createProductCategoryReq.setDescription("服装分类产品");
        createProductCategoryReq.setPicUrl("http://dummyimage.com/400x400");
        createProductCategoryReq.setSort(0);
        createProductCategoryReq.setPid(0);
        return createProductCategoryReq;
    }

    private CreateProductCategoryReq buildFailCreateProductCategoryReq() {
        CreateProductCategoryReq createProductCategoryReq = new CreateProductCategoryReq();
        createProductCategoryReq.setName("服装");
        createProductCategoryReq.setDescription("服装分类产品");
        createProductCategoryReq.setPicUrl("http://dummyimage.com/400x400");
        createProductCategoryReq.setSort(0);
        createProductCategoryReq.setPid(10);
        return createProductCategoryReq;
    }

    @Test
    public void getProductCategoryContentTestSuccess() throws Exception {
        String contentAsString = post("/mall/product/category", buildSuccessCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn().getResponse().getContentAsString();
        get("/mall/product/category/{category_id}".replace("{category_id}", contentAsString)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getProductCategoryContentTestFail() throws Exception {
        get("/mall/product/category/{category_id}".replace("{category_id}", "100")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateProductCategoryTestSuccess() throws Exception {
        String contentAsString = post("/mall/product/category", buildSuccessCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn().getResponse().getContentAsString();
        put("/mall/product/category/{category_id}".replace("{category_id}", contentAsString), buildUpdateProductCategoryReq())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateProductCategoryTestFail() throws Exception {
        String contentAsString = post("/mall/product/category", buildSuccessCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn().getResponse().getContentAsString();
        post("/mall/product/category", buildSuccessCreateProductCategoryReq2());
        put("/mall/product/category/{category_id}".replace("{category_id}", contentAsString), buildUpdateProductCategoryReq())
            .andExpect(MockMvcResultMatchers.status().isConflict());

    }

    private UpdateProductCategoryReq buildUpdateProductCategoryReq() {
        UpdateProductCategoryReq updateProductCategoryReq = new UpdateProductCategoryReq();
        updateProductCategoryReq.setName("服装");
        return updateProductCategoryReq;
    }

    @Test
    public void pageProductCategoryContentTestSuccess() throws Exception {
        get("/mall/product/category/page", buildPageQueryProductCategoryReq() ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private PageQueryProductCategoryReq buildPageQueryProductCategoryReq() {
        PageQueryProductCategoryReq pageQueryProductCategoryReq = new PageQueryProductCategoryReq();
        pageQueryProductCategoryReq.setPageNo(1);
        pageQueryProductCategoryReq.setPageSize(10);
        return pageQueryProductCategoryReq;
    }
}
