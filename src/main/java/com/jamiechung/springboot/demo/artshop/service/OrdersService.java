package com.jamiechung.springboot.demo.artshop.service;

import com.jamiechung.springboot.demo.artshop.domain.entity.Order;

import java.util.List;

public interface OrdersService {
    List<Order> findAll();

    Order findById(Long id);

    Order save(Order order);

    void deleteById(Long id);

}
