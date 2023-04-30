package com.jamiechung.springboot.demo.artshop.service;

import com.jamiechung.springboot.demo.artshop.domain.entity.UserRole;

import java.util.List;

public interface UserRolesService {
    List<UserRole> findAll();

    UserRole findById(Long id);

    UserRole save(UserRole userRole);

    void deleteById(Long id);
}
