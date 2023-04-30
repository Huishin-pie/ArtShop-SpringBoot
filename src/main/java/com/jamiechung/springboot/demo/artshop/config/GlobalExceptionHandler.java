package com.jamiechung.springboot.demo.artshop.config;

import com.jamiechung.springboot.demo.artshop.controller.exception.ServiceException;
import com.jamiechung.springboot.demo.artshop.domain.response.BaseResponse;
import com.jamiechung.springboot.demo.artshop.domain.response.ResponseInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<?> NotFoundExceptionHandle(ServiceException serviceException) {
        ResponseInfo info = new ResponseInfo();
        info.setStatus(HttpStatus.NOT_FOUND.value());
        info.setMessage(serviceException.getMessage());
        BaseResponse response = new BaseResponse(info, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
