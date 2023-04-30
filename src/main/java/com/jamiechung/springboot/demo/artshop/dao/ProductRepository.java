package com.jamiechung.springboot.demo.artshop.dao;

import com.jamiechung.springboot.demo.artshop.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;


public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findAll(Pageable pageable);
    Page<Product> findByCategoryId(@RequestParam("category_id") Long categoryId, Pageable pageable);
}
