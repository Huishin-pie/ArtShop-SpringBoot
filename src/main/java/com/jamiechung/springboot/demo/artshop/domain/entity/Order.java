package com.jamiechung.springboot.demo.artshop.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "order")
@Data
public class Order extends AbstractEntity {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id")
    private String productId;
}
