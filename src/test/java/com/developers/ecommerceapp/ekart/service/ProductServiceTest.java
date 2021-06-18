package com.developers.ecommerceapp.ekart.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.developers.ecommerceapp.ekart.dao.Category;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.exceptions.ProductNotExistException;
import com.developers.ecommerceapp.ekart.model.ProductDto;
import com.developers.ecommerceapp.ekart.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	
	@InjectMocks
	ProductService productService;
	@Mock
	ProductRepository productRepository;
	
	
	static ProductDto productDto;
	static Category category;
	static Product product;
	
	@BeforeAll
	public static void setUp() {
	     MockitoAnnotations.openMocks(ProductService.class);
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
		product.setCategory(category);
	}
	
	@Test
	@DisplayName("listProducts: Positive Scenario")
	public void listProductsTest() {
		List<Product> productList=new ArrayList<Product>();
		productList.add(product);
		when(productRepository.findAll()).thenReturn(productList);
		List<ProductDto> response=productService.listProducts();
		assertNotNull(response);
	}
	
	@Test
	@DisplayName("addProducts: Positive Scenario")
	public void addProductTest() {
		when(productRepository.save(Mockito.any())).thenReturn(product);
		Product response=productService.addProduct(productDto, category);
		assertNotNull(response);
	}
	
	@Test
	@DisplayName("updateProducts: Positive Scenario")
	public void updateProductTest() {
		when(productRepository.save(Mockito.any())).thenReturn(product);
		Product response=productService.updateProduct(1,productDto, category);
		assertNotNull(response);
	}
	
	@Test
	@DisplayName("getProductById: Positive Scenario")
	public void getProductByIdTest() {
		when(productRepository.findById(1)).thenReturn(Optional.of(product));
		Product response=productService.getProductById(1);
		assertNotNull(response);
	}
	
	@Test
	@DisplayName("getProductById: Negative Scenario")
	public void getProductByIdNegativeTest() {
		when(productRepository.findById(1)).thenReturn(Optional.empty());
		Assertions.assertThrows(ProductNotExistException.class, () -> {
			productService.getProductById(1);
		});
	}
}
