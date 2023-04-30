package com.jamiechung.springboot.demo.artshop.domain.entity;

import com.jamiechung.springboot.demo.artshop.domain.ProductStatusE;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product extends AbstractEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Integer price;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "quantity_available")
    private Integer quantityAvailable;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatusE status;
    @Column(name = "product_img_id")
    private Long productImgId;

}
