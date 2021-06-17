package com.developers.ecommerceapp.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developers.ecommerceapp.ekart.dao.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
