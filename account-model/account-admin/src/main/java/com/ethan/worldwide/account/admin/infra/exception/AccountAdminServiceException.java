package com.ethan.worldwide.account.admin.infra.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Author zhenghui
 * @Description 商品服务异常
 * @Date 2022/6/26
 */
@Data
public class AccountAdminServiceException extends RuntimeException {

    private HttpStatus status;

    public AccountAdminServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public static void assertException(HttpStatus status, String message) {
        throw new AccountAdminServiceException(status, message);
    }
}
