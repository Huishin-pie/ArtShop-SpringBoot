package com.jamiechung.springboot.demo.artshop.service.impl;

import com.jamiechung.springboot.demo.artshop.controller.exception.DataNotFoundException;
import com.jamiechung.springboot.demo.artshop.dao.RoleRepository;
import com.jamiechung.springboot.demo.artshop.domain.entity.Role;
import com.jamiechung.springboot.demo.artshop.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {
    private RoleRepository roleRepository;

    @Autowired
    public RolesServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Can not find role id - " + id));
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
