package com.jamiechung.springboot.demo.artshop.controller;

import com.fasterxml.jackson.databind.DatabindContext;
import com.jamiechung.springboot.demo.artshop.domain.vo.UserVo;
import com.jamiechung.springboot.demo.artshop.domain.entity.User;
import com.jamiechung.springboot.demo.artshop.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        String message = "";

        Optional<String> validationResult = usersService.ValidateUsers(user);
        if (!validationResult.isEmpty()) {
            message = validationResult.get();
            return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
        }

        UserVo userVO = usersService.login(user);
        if (userVO == null) {
            message = "Email or password is invalid.";
            return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
        }

        message = "success";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/register")
    public ResponseEntity<?> doRegister(@RequestBody UserVo user) {
        String message = "";
        Optional<String> validationResult = usersService.ValidateUserVo(user);
        if (!validationResult.isEmpty()) {
            message = validationResult.get();
            return ResponseEntity.ok(message);
        }

        Optional<String> optional = usersService.register(user);
        if (!optional.isEmpty()) {
            message = optional.get();
        } else {
            message = "success";
        }
        return ResponseEntity.ok(message);
    }
}
