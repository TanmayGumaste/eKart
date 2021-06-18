package com.developers.ecommerceapp.ekart.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.developers.ecommerceapp.ekart.dao.Category;
import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.model.AddToCartDto;
import com.developers.ecommerceapp.ekart.model.ApiResponse;
import com.developers.ecommerceapp.ekart.model.CartDto;
import com.developers.ecommerceapp.ekart.model.CartItemDto;
import com.developers.ecommerceapp.ekart.repository.CustomerRepository;
import com.developers.ecommerceapp.ekart.service.CartService;
import com.developers.ecommerceapp.ekart.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

	@InjectMocks
	CartController cartController;
	@Mock
	CartService cartService;
	@Mock
    CustomerRepository customerRepository;
	@Mock
	ProductService productService;
	
	static AddToCartDto addToCart;
	static Product product;
	static CustomerEntity customer;
	static CartItemDto cartItemDto;
	
	@BeforeAll
	public static void setUp() {
		addToCart=new AddToCartDto();
		customer=new CustomerEntity();
		product=new Product();
		cartItemDto=new CartItemDto();
		
		addToCart.setId(1);
		addToCart.setProductId(2);
		addToCart.setQuantity(3);
		
		customer.setCustId(1);
		customer.setEmail("abc@test.com");
		customer.setFirstName("abc");
		customer.setLastName("def");
		
		product.setId(3);
		product.setName("Product Name");
		
		cartItemDto.setId(1);
		cartItemDto.setQuantity(1);
		cartItemDto.setUserId(2);
	}
	
	@Test
	@DisplayName("get categories: Positive Scenario")
	public void addToCartTest() {
		when(customerRepository.findByCustId(1)).thenReturn(customer);
		when(productService.getProductById(addToCart.getProductId())).thenReturn(product);
		//doNothing().when(fundService).fundTransfer(transferBalanceRequest);
		doNothing().when(cartService).addToCart(addToCart, product, customer);
		ResponseEntity<ApiResponse> response=cartController.addToCart(addToCart, 1);
		assertEquals("Added to cart", response.getBody().getMessage());;
	}
	
	@Test
	@DisplayName("getCartItems: Positive Scenario")
	public void getCartItems() {
		List<CartItemDto> listCartItem = new ArrayList<CartItemDto>();
		listCartItem.add(cartItemDto);
		CartDto cartDto = new CartDto(listCartItem, 1000.00);
		when(customerRepository.findByCustId(1)).thenReturn(customer);
		when(cartService.listCartItems(customer)).thenReturn(cartDto);
		ResponseEntity<CartDto> response = cartController.getCartItems(1);
		assertNotNull(response);
	}
	
	@Test
	@DisplayName("getCartItems: Positive Scenario")
	public void deleteCartItem() {
		doNothing().when(cartService).deleteCartItem(1, 3);
		ResponseEntity<ApiResponse> response=cartController.deleteCartItem(1, 3);
		assertEquals("Item has been removed", response.getBody().getMessage());
	}
}
