package com.developers.ecommerceapp.ekart.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.developers.ecommerceapp.ekart.dao.Category;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.repository.Categoryrepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	
	@InjectMocks
	CategoryService categoryService;
	
	@Mock
	Categoryrepository categoryrepository;
	
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
	@DisplayName("createCategory: Negative Scenario Save Failed")
	public void createCategoryTest() {
	when(categoryrepository.save(ArgumentMatchers.any(Category.class))).thenReturn(category);
	Category response=categoryService.createCategory(category);
    assertNotNull(response);
	}
	
	@Test
	@DisplayName("createCategory: Negative Scenario Save Failed")
	public void createCategoryNegativeTest() {
	when(categoryrepository.save(ArgumentMatchers.any(Category.class))).thenThrow(NullPointerException.class);
	Assertions.assertThrows(NullPointerException.class, () -> {
		categoryService.createCategory(category);
	});
	}
	
	@Test
	@DisplayName("updateCategory: Positive Scenario Save")
	public void updateCategoryTest() {
	Category newCategory= new Category();
	newCategory.setCategoryName("new demo category");
	when(categoryrepository.save(ArgumentMatchers.any(Category.class))).thenReturn(newCategory);
	Category response=categoryService.createCategory(category);
    assertNotNull(response);
	}
	
}
