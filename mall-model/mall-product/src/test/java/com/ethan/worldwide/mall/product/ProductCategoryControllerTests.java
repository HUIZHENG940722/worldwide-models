package com.ethan.worldwide.mall.product;

import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductCategoryReq;
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
        post("/product/category", buildSuccessCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createProductCategoryTestFail() throws Exception {
        post("/product/category", buildFailCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isNotFound());
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
        String contentAsString = post("/product/category", buildSuccessCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn().getResponse().getContentAsString();
        get("/product/category/{category_id}".replace("{category_id}", contentAsString)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getProductCategoryContentTestFail() throws Exception {
        get("/product/category/{category_id}".replace("{category_id}", "100")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateProductCategoryTestSuccess() throws Exception {
        String contentAsString = post("/product/category", buildSuccessCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn().getResponse().getContentAsString();
        put("/product/category/{category_id}".replace("{category_id}", contentAsString), buildUpdateProductCategoryReq())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateProductCategoryTestFail() throws Exception {
        String contentAsString = post("/product/category", buildSuccessCreateProductCategoryReq()).andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn().getResponse().getContentAsString();
        post("/product/category", buildSuccessCreateProductCategoryReq2());
        put("/product/category/{category_id}".replace("{category_id}", contentAsString), buildUpdateProductCategoryReq())
            .andExpect(MockMvcResultMatchers.status().isConflict());

    }

    private UpdateProductCategoryReq buildUpdateProductCategoryReq() {
        UpdateProductCategoryReq updateProductCategoryReq = new UpdateProductCategoryReq();
        updateProductCategoryReq.setName("服装");
        return updateProductCategoryReq;
    }
}
