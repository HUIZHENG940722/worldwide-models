package com.ethan.worldwide.mall.product;

import com.ethan.worldwide.openapi.interfaces.api.dto.CreateProductCategoryReq;
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

    private CreateProductCategoryReq buildFailCreateProductCategoryReq() {
        CreateProductCategoryReq createProductCategoryReq = new CreateProductCategoryReq();
        createProductCategoryReq.setName("服装");
        createProductCategoryReq.setDescription("服装分类产品");
        createProductCategoryReq.setPicUrl("http://dummyimage.com/400x400");
        createProductCategoryReq.setSort(0);
        createProductCategoryReq.setPid(10);
        return createProductCategoryReq;
    }
}
