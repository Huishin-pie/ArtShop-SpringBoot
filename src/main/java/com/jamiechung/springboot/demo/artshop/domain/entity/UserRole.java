package com.jamiechung.springboot.demo.artshop.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user_role")
@Data
public class UserRole extends AbstractEntity {
    @Column(name="user_id")
    private Integer userId;
    @Column(name="role_id")
    private String roleId;

}
