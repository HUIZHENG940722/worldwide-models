package com.ethan.worldwide.account.admin.controller;

import com.ethan.worldwide.account.admin.AccountAdminApplicationTests;
import com.ethan.worldwide.openapi.interfaces.api.dto.AddAdminReq;
import com.ethan.worldwide.openapi.interfaces.api.dto.LoginAdminReq;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Author zhenghui
 * @Description 后台用户单元测试
 * @Date 2022/7/4
 */
public class AdminUserControllerTests extends AccountAdminApplicationTests {

    @Test
    public void loginSuccessTest() throws Exception {
        post("/account/admin/login", buildLoginRequestReqSuccess()).andExpect(MockMvcResultMatchers.status().isOk());
    }

    private LoginAdminReq buildLoginRequestReqSuccess() {
        LoginAdminReq loginAdminReq = new LoginAdminReq();
        loginAdminReq.setUsername("admin");
        loginAdminReq.setPassword("123456");
        return loginAdminReq;
    }

    @Test
    public void loginFailTest() throws Exception {
        post("/account/admin/login", buildLoginRequestReqFail()).andExpect(MockMvcResultMatchers.status().isConflict());
    }

    private LoginAdminReq buildLoginRequestReqFail() {
        LoginAdminReq loginAdminReq = new LoginAdminReq();
        loginAdminReq.setUsername("admin");
        loginAdminReq.setPassword("1234578");
        return loginAdminReq;
    }

    @Test
    public void sysAdminAddAdminUserSuccessTest() throws Exception {
        post("/account/admin/1", buildAddAdminReq()).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private AddAdminReq buildAddAdminReq() {
        AddAdminReq addAdminReq = new AddAdminReq();
        addAdminReq.setUsername("test");
        addAdminReq.setPassword("123456");
        return addAdminReq;
    }
}
