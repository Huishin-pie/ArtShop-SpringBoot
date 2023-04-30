package com.jamiechung.springboot.demo.artshop.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="product_img")
@Data
public class ProductImg extends AbstractEntity {
    @Column(name="product_id")
    private Integer productId;
    @Column(name="file_name")
    private String fileName;
    @Column(name="display_name")
    private String displayName;
    @Column(name="length")
    private String length;
    @Column(name="url")
    private String url;
}
