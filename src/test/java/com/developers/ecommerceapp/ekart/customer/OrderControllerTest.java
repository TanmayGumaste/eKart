package com.developers.ecommerceapp.ekart.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.developers.ecommerceapp.ekart.dao.Category;
import com.developers.ecommerceapp.ekart.dao.Order;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.model.ApiResponse;
import com.developers.ecommerceapp.ekart.model.ProductDto;
import com.developers.ecommerceapp.ekart.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
	
	@InjectMocks
	OrderController controller;
	
	@Mock
	OrderService orderService;

	static Order order;
	
	@BeforeAll
	public static void setUp() {
		order = new Order();
		order.setId(1);
	}
	
	@Test
	@DisplayName("checkout Test: Positive Scenario")
	public void checkout() {
		when(orderService.checkOut(1)).thenReturn(order);
		ResponseEntity<ApiResponse>response=controller.checkOut(1);
		assertEquals("Order placed successfully", response.getBody().getMessage());
	}
	
	@Test
	@DisplayName("order history Test: Positive Scenario")
	public void getOrderHistory() {
		List<Order> list = new ArrayList<>();
		when(orderService.listOrders(1)).thenReturn(list);
		assertNotNull(controller.getOrderHistory(1));
	}
}
