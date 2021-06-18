package com.developers.ecommerceapp.ekart.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.developers.ecommerceapp.ekart.service.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
	
	@InjectMocks
	CategoryController categoryController;
	
	@Mock
	CategoryService categoryService;
	
	static Category category;
	static Product product;

	@BeforeAll
	public static void setUp() {
		category=new Category();
		product=new Product();
		
		category.setId(1);
		
		product.setCategory(category);
		product.setId(1);
		product.setName("test product");
		product.setPrice(2000.00);
		Set<Product> products = new HashSet<>();
		products.add(product);
		category.setDescription("test category");
		category.setProducts(products);
	}
	
	@Test
	@DisplayName("get categories: Positive Scenario")
	public void getCategoriesTest() {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(category);
		when(categoryService.listCategories()).thenReturn(categoryList);
		ResponseEntity<List<Category>> response=categoryController.getCategories();
		assertNotNull(response.getBody());
	}
	
	@Test
	@DisplayName("create categories: Positive Scenario")
	public void createCategoriesTest() {
		when(categoryService.readCategory(category.getCategoryName())).thenReturn(null);
		when(categoryService.createCategory(category)).thenReturn(category);
		ResponseEntity<ApiResponse> response=categoryController.createCategory(category);
		assertEquals("created the category", response.getBody().getMessage());
	}
	
	@Test
	@DisplayName("create categories: Negative Scenario")
	public void createCategoriesNegativeTest() {
		when(categoryService.readCategory(category.getCategoryName())).thenReturn(category);
		ResponseEntity<ApiResponse> response=categoryController.createCategory(category);
		assertEquals("category already exists", response.getBody().getMessage());
		
	}
	
}
