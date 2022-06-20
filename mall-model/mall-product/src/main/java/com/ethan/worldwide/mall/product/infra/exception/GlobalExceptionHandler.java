package com.ethan.worldwide.mall.product.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketTimeoutException;

/**
 * @Author zhenghui
 * @Description 全局异常处理
 * @Date 2022/6/26
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理商品异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MallProductServiceException.class)
    public ResponseEntity handleMallProductServiceException(MallProductServiceException e) {
        String message = e.getMessage();
        return new ResponseEntity(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(value = SocketTimeoutException.class)
    public ResponseEntity handleSocketTimeoutException(SocketTimeoutException e) {
        String message = e.getMessage();
        return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
