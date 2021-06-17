package com.developers.ecommerceapp.ekart.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.developers.ecommerceapp.ekart.dao.Category;
import com.developers.ecommerceapp.ekart.repository.Categoryrepository;



@Service
@Transactional
public class CategoryService {
	Logger logger = LoggerFactory.getLogger(CategoryService.class);

	private final Categoryrepository categoryrepository;

	public CategoryService(Categoryrepository categoryrepository) {
		this.categoryrepository = categoryrepository;
	}

	public List<Category> listCategories() {
		return categoryrepository.findAll();
	}

	public Category createCategory(Category category) {
		logger.info("CategoryService : createCategory : Start");
		return categoryrepository.save(category);
	}

	public Category readCategory(String categoryName) {
		return categoryrepository.findByCategoryName(categoryName);
	}

	public Optional<Category> readCategory(Integer categoryId) {
		return categoryrepository.findById(categoryId);
	}

	public void updateCategory(Integer categoryID, Category newCategory) {
		Category category = categoryrepository.findById(categoryID).get();
		category.setCategoryName(newCategory.getCategoryName());
		category.setDescription(newCategory.getDescription());
		category.setProducts(newCategory.getProducts());
		categoryrepository.save(category);
	}
}
