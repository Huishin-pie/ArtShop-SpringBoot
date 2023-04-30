package com.jamiechung.springboot.demo.artshop.domain.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ResponseInfo {
    private Integer status;
    private String message;
    private LocalDateTime dateTime;

    public ResponseInfo() {
        this.dateTime = LocalDateTime.now();
    }

    public ResponseInfo(Integer status, String message, LocalDateTime dateTime) {
        this.status = status;
        this.message = message;
        this.dateTime = dateTime;
    }

    public ResponseInfo(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.dateTime = LocalDateTime.now();
    }
}
