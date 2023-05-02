package com.jamiechung.springboot.demo.artshop.controller;

import com.jamiechung.springboot.demo.artshop.controller.exception.DataNotFoundException;
import com.jamiechung.springboot.demo.artshop.domain.entity.Category;
import com.jamiechung.springboot.demo.artshop.domain.response.BaseResponse;
import com.jamiechung.springboot.demo.artshop.domain.response.ResponseInfo;
import com.jamiechung.springboot.demo.artshop.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategoryList(){
      List<Category> result= categoryService.findAll();
      if(result.isEmpty()){
            throw new DataNotFoundException("Can not find categories.");
      }
        ResponseInfo info = new ResponseInfo(HttpStatus.OK.value(), "success");
        BaseResponse response = new BaseResponse(info, result);
        return ResponseEntity.ok(response);
    }
}
