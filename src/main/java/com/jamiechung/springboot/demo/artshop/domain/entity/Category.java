package com.jamiechung.springboot.demo.artshop.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="category")
@Data
public class Category extends AbstractEntity {
    @Column(name="name")
    private String name;
    @Column(name="sort")
    private Integer sort;

}
