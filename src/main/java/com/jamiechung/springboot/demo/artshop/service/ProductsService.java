package com.jamiechung.springboot.demo.artshop.service;

import com.jamiechung.springboot.demo.artshop.domain.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductsService {
    Page<Product> findAll(int pageNo, int pageSize);

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    String validateProduct(Product product);

    Product createProduct(Product product);

    Page<Product> findByCategoryId(Long categoryId, int pageNo, int pageSize);
}
