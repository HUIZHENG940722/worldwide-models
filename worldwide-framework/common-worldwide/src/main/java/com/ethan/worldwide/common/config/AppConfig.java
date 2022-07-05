package com.ethan.worldwide.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author zhenghui
 * @Description 系统管理员用户配置
 * @Date 2022/7/4
 */
@Data
@Component
@ConfigurationProperties(prefix = "worldwide.app.admin")
public class AppConfig {

    @Value(value = "username")
    private String username;

    @Value(value = "password")
    private String password;
}
