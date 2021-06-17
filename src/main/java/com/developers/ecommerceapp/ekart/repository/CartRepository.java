package com.developers.ecommerceapp.ekart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developers.ecommerceapp.ekart.dao.Cart;
import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    //List<Cart> findAllByUserOrderByCreatedDateDesc(CustomerEntity customerEntity);
	List<Cart> findAllByCustomerOrderByCreatedDateDesc(CustomerEntity customerEntity);
	List<Cart> deleteByCustomer(CustomerEntity customerEntity);
   // List<Cart> deleteByUser(CustomerEntity customerEntity);

}

