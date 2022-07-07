package com.ethan.worldwide.mall.product;

import cn.hutool.json.JSONUtil;
import com.ethan.worldwide.common.config.AppConfig;
import com.ethan.worldwide.mall.product.application.service.feign.IAdminUserService;
import com.ethan.worldwide.openapi.interfaces.api.dto.LoginAdminReq;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@EnableEurekaClient
@EnableFeignClients
public class MallProductApplicationTests {

    private static String accessToken;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @BeforeEach
    void setUp() throws Exception {
        ServiceInstance choose = loadBalancerClient.choose("ACCOUNT-ADMIN");
        URI uri = choose.getUri();
        String loginUrl = uri + "/admin_user/login";
        LoginAdminReq loginAdminReq = new LoginAdminReq();
        loginAdminReq.setUsername(appConfig.getUsername());
        loginAdminReq.setPassword(appConfig.getPassword());
        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post(loginUrl, loginAdminReq)).andReturn().getResponse().getContentAsString();
        accessToken = "Bearer " + contentAsString;
    }

    @Autowired
    private MockMvc mockMvc;

    protected ResultActions post(String url, Object body) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", accessToken)
            .content(JSONUtil.toJsonStr(body)));
    }

    protected ResultActions put(String url, Object body) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", accessToken)
            .content(JSONUtil.toJsonStr(body)));
    }

    protected ResultActions get(String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(url).header("Authorization", accessToken));
    }

    protected ResultActions get(String url, Object body) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", accessToken)
            .content(JSONUtil.toJsonStr(body)));
    }

    protected ResultActions delete(String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.delete(url).header("Authorization", accessToken));
    }
}
