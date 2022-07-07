package com.ethan.worldwide.mall.product.application.service.feign;

import com.ethan.worldwide.common.bo.AuthenticationUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author zWX1058539
 * @Description 后台用户服务器客户端
 * @Date 2022/7/6
 */
@FeignClient(value = "account-admin")
public interface IAdminUserService {

    @GetMapping(value = "/loadUserByUsername")
    AuthenticationUser loadUserByUsername(@RequestParam(value = "username") String username);
}
