package com.jamiechung.springboot.demo.artshop.service.impl;

import com.jamiechung.springboot.demo.artshop.controller.exception.DataNotFoundException;
import com.jamiechung.springboot.demo.artshop.dao.OrderRepository;
import com.jamiechung.springboot.demo.artshop.domain.entity.Order;
import com.jamiechung.springboot.demo.artshop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService {
    private OrderRepository orderRepository;

    @Autowired
    public OrdersServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Can not find order id - " + id));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
