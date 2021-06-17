package com.developers.ecommerceapp.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developers.ecommerceapp.ekart.dao.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {
}
