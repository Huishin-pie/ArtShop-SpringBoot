package com.jamiechung.springboot.demo.artshop.service;

import com.jamiechung.springboot.demo.artshop.domain.entity.Category;
import com.jamiechung.springboot.demo.artshop.domain.entity.Order;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    void deleteById(Long id);
}
