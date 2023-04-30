package com.jamiechung.springboot.demo.artshop.controller.exception;

public class DataNotFoundException extends ServiceException{
    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }
}
