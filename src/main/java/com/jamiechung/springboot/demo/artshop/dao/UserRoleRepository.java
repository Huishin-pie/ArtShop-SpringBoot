package com.jamiechung.springboot.demo.artshop.dao;

import com.jamiechung.springboot.demo.artshop.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
