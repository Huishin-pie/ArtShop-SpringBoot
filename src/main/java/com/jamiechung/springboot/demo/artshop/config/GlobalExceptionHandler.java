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
    protected ResponseEntity<?> RuntimeExceptionHandle(ServiceException serviceException) {
        ResponseInfo info = new ResponseInfo();
        info.setStatus(HttpStatus.NOT_FOUND.value());
        info.setMessage(serviceException.getMessage());
        BaseResponse response = new BaseResponse(info, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<?> RuntimeExceptionHandle(RuntimeException runtimeException) {
        ResponseInfo info = new ResponseInfo();
        info.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        info.setMessage(runtimeException.getMessage());
        BaseResponse response = new BaseResponse(info, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
