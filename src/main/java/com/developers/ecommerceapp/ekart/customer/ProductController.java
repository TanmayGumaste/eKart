package com.developers.ecommerceapp.ekart.customer;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developers.ecommerceapp.ekart.dao.Category;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.model.ApiResponse;
import com.developers.ecommerceapp.ekart.model.ProductDto;
import com.developers.ecommerceapp.ekart.service.CategoryService;
import com.developers.ecommerceapp.ekart.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired 
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Invalid Category",0), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        Product prod = productService.addProduct(productDto, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been added",prod.getId()), HttpStatus.CREATED);
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Valid ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Invalid Category",0), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        Product prod = productService.updateProduct(productID, productDto, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated",prod.getId()), HttpStatus.OK);
    }
}
