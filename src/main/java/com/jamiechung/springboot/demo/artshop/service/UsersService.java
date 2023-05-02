package com.jamiechung.springboot.demo.artshop.service;

import com.jamiechung.springboot.demo.artshop.domain.entity.User;
import com.jamiechung.springboot.demo.artshop.domain.vo.UserVo;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<User> findAll();

    User findById(Long id);

    User save(User user);

    void deleteById(Long id);

    User findUserByEmail(String email);

    UserVo login(User user);

    String register(UserVo userVo);

    String ValidateUser(User user);

    String ValidateUserVo(UserVo user);
}
