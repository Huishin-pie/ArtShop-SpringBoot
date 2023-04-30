package com.jamiechung.springboot.demo.artshop.dao;

import com.jamiechung.springboot.demo.artshop.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
