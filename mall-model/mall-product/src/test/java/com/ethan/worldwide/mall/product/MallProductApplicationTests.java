package com.ethan.worldwide.mall.product;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MallProductApplicationTests {

    private static String accessToken;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() throws Exception {
        accessToken = "Bearer " + "eyJhbGciOiJIUzUxMiJ9" +
            ".eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjQyNTA5NjE0NDczNTQ4LCJleHAiOjQyNTEwMjIwMDkwfQ" +
            ".r9OtuL6M4QZOSoNhEDwnZg-yU0GZvaAWXFdsP0ARn_QKuQnnARsdSs6OnRdATW4vEMF-KTAFty6f_Q8QFCK24w";
    }

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
