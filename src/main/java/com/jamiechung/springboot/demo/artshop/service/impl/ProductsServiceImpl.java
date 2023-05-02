package com.jamiechung.springboot.demo.artshop.service.impl;

import com.jamiechung.springboot.demo.artshop.controller.exception.DataNotFoundException;
import com.jamiechung.springboot.demo.artshop.dao.ProductRepository;
import com.jamiechung.springboot.demo.artshop.domain.entity.Product;
import com.jamiechung.springboot.demo.artshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    private ProductRepository productRepository;

    @Autowired
    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Can not find product id - " + id));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public String validateProduct(Product product) {
        if (product.getName() == null)
            return "Name can't be blank.";
        if (product.getCategoryId() == null)
            return "Category id can't be blank.";
        if (product.getQuantity() == null)
            return "Quantity can't be blank.";
        return "";
    }

    @Override
    public Product createProduct(Product product) {
        Product saveProduct = new Product();
        if (product.getName() != null)
            saveProduct.setName(product.getName());
        if (product.getCategoryId() != null)
            saveProduct.setCategoryId(product.getCategoryId());
        if (product.getStatus() != null)
            saveProduct.setStatus(product.getStatus());
        if (product.getPrice() != null)
            saveProduct.setPrice(product.getPrice());
        if (product.getQuantity() != null)
            saveProduct.setQuantity(product.getQuantity());
        if (product.getDescription() != null)
            saveProduct.setDescription(product.getDescription());
        saveProduct.setCreateUser("Admin");
        saveProduct.setCreateDate(LocalDateTime.now());
        return save(saveProduct);
    }

    @Override
    public Page<Product> findByCategoryId(Long categoryId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findByCategoryId(categoryId, pageable);
    }
}
