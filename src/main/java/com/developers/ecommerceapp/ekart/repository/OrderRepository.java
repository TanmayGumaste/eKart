package com.developers.ecommerceapp.ekart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.dao.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findAllByUserOrderByCreatedDateDesc(CustomerEntity user);
	

}
