package com.developers.ecommerceapp.ekart.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developers.ecommerceapp.ekart.dao.OrderItem;
import com.developers.ecommerceapp.ekart.repository.OrderItemsRepository;

@Service
@Transactional
public class OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public void addOrderedProducts(OrderItem orderItem) {
    	orderItemsRepository.save(orderItem);
    }


}
