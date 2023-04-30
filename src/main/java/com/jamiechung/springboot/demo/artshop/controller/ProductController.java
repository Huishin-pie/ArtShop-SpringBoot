package com.jamiechung.springboot.demo.artshop.controller;

import com.jamiechung.springboot.demo.artshop.controller.exception.DataNotFoundException;
import com.jamiechung.springboot.demo.artshop.controller.exception.ValidationException;
import com.jamiechung.springboot.demo.artshop.domain.entity.Product;
import com.jamiechung.springboot.demo.artshop.domain.response.BaseResponse;
import com.jamiechung.springboot.demo.artshop.domain.response.ResponseInfo;
import com.jamiechung.springboot.demo.artshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ProductController {
    private ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProductList(@RequestParam int pageNo, int pageSize) {
        if (pageSize == 0) pageSize = 10;
        Page<Product> pageResult = productsService.findAll(pageNo, pageSize);
        if (!pageResult.hasContent()) {
            throw new DataNotFoundException("Can not find products.");
        }
        ResponseInfo info = new ResponseInfo(HttpStatus.OK.value(), "success");
        BaseResponse response = new BaseResponse(info, pageResult);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/category")
    public ResponseEntity<?> getProductListByCategory(@RequestParam Long categoryId, int pageNo, int pageSize) {
        if (pageSize == 0) pageSize = 10;
        Page<Product> pageResult = productsService.findByCategoryId(categoryId, pageNo, pageSize);
        if (!pageResult.hasContent()) {
            throw new DataNotFoundException("Can not find products.");
        }
        ResponseInfo info = new ResponseInfo(HttpStatus.OK.value(), "success");
        BaseResponse response = new BaseResponse(info, pageResult);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        String validationResult = productsService.validateProduct(product);
        if (StringUtils.hasLength(validationResult)) {
            throw new ValidationException(validationResult);
        }
        Product saveProduct = productsService.createProduct(product);
        ResponseInfo info = new ResponseInfo(HttpStatus.OK.value(), "success");
        BaseResponse response = new BaseResponse(info, saveProduct);
        return ResponseEntity.ok(response);
    }


}
