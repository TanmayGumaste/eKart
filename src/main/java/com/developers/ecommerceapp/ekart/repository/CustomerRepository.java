package com.developers.ecommerceapp.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developers.ecommerceapp.ekart.dao.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	CustomerEntity findByEmail(String email);
	CustomerEntity findByCustId(Integer id);

}
