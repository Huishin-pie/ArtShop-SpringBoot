package com.jamiechung.springboot.demo.artshop.service;

import com.jamiechung.springboot.demo.artshop.domain.entity.Role;

import java.util.List;

public interface RolesService {
    List<Role> findAll();

    Role findById(Long id);

    Role save(Role role);

    void deleteById(Long id);
}
