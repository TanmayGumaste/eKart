package com.developers.ecommerceapp.ekart.customer;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developers.ecommerceapp.ekart.dao.Category;
import com.developers.ecommerceapp.ekart.model.ApiResponse;
import com.developers.ecommerceapp.ekart.service.CategoryService;
import com.developers.ecommerceapp.ekart.utils.Helper;


@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
		logger.info("CategoryController : getCategories : Start");
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
    }
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
		logger.info("CategoryController : createCategory : Start");
		Category cat = categoryService.readCategory(category.getCategoryName());
		if (Helper.notNull(cat)) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists",cat.getId()), HttpStatus.CONFLICT);
		}
		cat = categoryService.createCategory(category);
		logger.info("CategoryController : createCategory : End");
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category",cat.getId()), HttpStatus.CREATED);
	}

}
