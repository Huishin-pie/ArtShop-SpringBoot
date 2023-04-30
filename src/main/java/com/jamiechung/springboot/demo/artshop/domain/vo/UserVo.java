package com.jamiechung.springboot.demo.artshop.domain.vo;

import lombok.Data;

@Data
public class UserVo {
    private String email;
    private String password;
    private String checkPassword;
    private String firstName;



}
