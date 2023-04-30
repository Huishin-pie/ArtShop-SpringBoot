package com.jamiechung.springboot.demo.artshop.service.impl;

import com.jamiechung.springboot.demo.artshop.dao.UserRoleRepository;
import com.jamiechung.springboot.demo.artshop.domain.entity.UserRole;
import com.jamiechung.springboot.demo.artshop.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRolesServiceImpl implements UserRolesService {
    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRolesServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole findById(Long id) {
        Optional<UserRole> userRolesOpt = userRoleRepository.findById(id);
        UserRole userRole = userRolesOpt.orElseThrow(() -> new RuntimeException("Did not find userRole id - " + id));
        return userRole;
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }
}
