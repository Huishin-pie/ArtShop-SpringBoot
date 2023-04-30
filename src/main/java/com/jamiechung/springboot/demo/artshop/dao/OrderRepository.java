package com.jamiechung.springboot.demo.artshop.dao;

import com.jamiechung.springboot.demo.artshop.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
