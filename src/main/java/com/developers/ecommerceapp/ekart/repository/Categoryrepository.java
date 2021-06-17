package com.developers.ecommerceapp.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developers.ecommerceapp.ekart.dao.Category;


@Repository
public interface Categoryrepository extends JpaRepository<Category, Integer> {
	Category findByCategoryName(String categoryName);

}
