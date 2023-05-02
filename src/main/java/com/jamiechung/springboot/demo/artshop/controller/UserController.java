package com.jamiechung.springboot.demo.artshop.controller;

import com.fasterxml.jackson.databind.DatabindContext;
import com.jamiechung.springboot.demo.artshop.controller.exception.AuthorizedException;
import com.jamiechung.springboot.demo.artshop.controller.exception.ValidationException;
import com.jamiechung.springboot.demo.artshop.domain.response.BaseResponse;
import com.jamiechung.springboot.demo.artshop.domain.response.ResponseInfo;
import com.jamiechung.springboot.demo.artshop.domain.vo.UserVo;
import com.jamiechung.springboot.demo.artshop.domain.entity.User;
import com.jamiechung.springboot.demo.artshop.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private DatabindContext session;
    private UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody User user) {
        String validationResult = usersService.ValidateUser(user);
        if (StringUtils.hasLength(validationResult)) {
            throw new ValidationException(validationResult);
        }
        UserVo userVO = usersService.login(user);
        if (userVO == null) {
            throw new AuthorizedException("Email or password is invalid.");
        }
        ResponseInfo info = new ResponseInfo(HttpStatus.OK.value(), "success");
        BaseResponse response = new BaseResponse(info, null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> doRegister(@RequestBody UserVo user) {
        String validationResult = usersService.ValidateUserVo(user);
        if (StringUtils.hasLength(validationResult)) {
            throw new ValidationException(validationResult);
        }
        String registerResult = usersService.register(user);
        if (StringUtils.hasLength(registerResult)) {
            throw new AuthorizedException(registerResult);
        }
        ResponseInfo info = new ResponseInfo(HttpStatus.OK.value(), "success");
        BaseResponse response = new BaseResponse(info, null);
        return ResponseEntity.ok(response);
    }
}
