package com.developers.ecommerceapp.ekart.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.developers.ecommerceapp.ekart.dao.Category;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.model.ApiResponse;
import com.developers.ecommerceapp.ekart.model.ProductDto;
import com.developers.ecommerceapp.ekart.service.CategoryService;
import com.developers.ecommerceapp.ekart.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
	
	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService productService;
	@Mock
	CategoryService categoryService;
	
	static ProductDto productDto;
	static Category category;
	static Product product;
	
	@BeforeAll
	public static void setUp() {
		productDto=new ProductDto();
		category=new Category();
		product=new Product();
		productDto.setId(1);
		productDto.setDescription("product description");
		productDto.setName("test name");
		productDto.setCategoryId(1);
		category.setId(1);
		product.setId(1);
		product.setName("testing product");
	}
	
	@Test
	@DisplayName("Add Product Test: Positive Scenario")
	public void addproductTestPositive() {
		when(categoryService.readCategory(productDto.getCategoryId())).thenReturn(Optional.of(category));
		when(productService.addProduct(productDto, category)).thenReturn(product);
		ResponseEntity<ApiResponse>response=productController.addProduct(productDto);
		assertEquals("Product has been added", response.getBody().getMessage());
	}
	@Test
	@DisplayName("Add Product Test: Negative Scenario")
	public void addproductTestNegative() {
		when(categoryService.readCategory(productDto.getCategoryId())).thenReturn(Optional.empty());
		ResponseEntity<ApiResponse>response=productController.addProduct(productDto);
		assertEquals("Invalid Category", response.getBody().getMessage());
	}
	
	@Test
	@DisplayName("getProducts: Positive Scenario")
	public void getProducts() {
		List<ProductDto>productList=new ArrayList<ProductDto>();
		productList.add(productDto);
		when(productService.listProducts()).thenReturn(productList);
		ResponseEntity<List<ProductDto>> response=productController.getProducts();
		assertNotNull(response.getBody());
	}
	
	@Test
	@DisplayName("Update Product Test: Positive Scenario")
	public void updateProductTest(){
		when(categoryService.readCategory(productDto.getCategoryId())).thenReturn(Optional.of(category));
		when(productService.updateProduct(1,productDto, category)).thenReturn(product);
		ResponseEntity<ApiResponse>response=productController.updateProduct(1, productDto);
		assertEquals("Product has been updated", response.getBody().getMessage());
	}
	@Test
	@DisplayName("Update Product Test: Negative Scenario")
	public void updateProductTestNegative(){
		when(categoryService.readCategory(productDto.getCategoryId())).thenReturn(Optional.empty());
		ResponseEntity<ApiResponse>response=productController.updateProduct(1, productDto);
		assertEquals("Invalid Category", response.getBody().getMessage());
	}

}
