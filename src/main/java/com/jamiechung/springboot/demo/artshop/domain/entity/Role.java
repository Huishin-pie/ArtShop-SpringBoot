package com.jamiechung.springboot.demo.artshop.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role extends AbstractEntity {
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "privileges")
    private String privileges;

}
