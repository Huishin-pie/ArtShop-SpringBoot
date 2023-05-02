package com.jamiechung.springboot.demo.artshop.controller.exception;

public class AuthorizedException extends ServiceException{
    public AuthorizedException(String message) {
        super(message);
    }

    public AuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizedException(Throwable cause) {
        super(cause);
    }
}
