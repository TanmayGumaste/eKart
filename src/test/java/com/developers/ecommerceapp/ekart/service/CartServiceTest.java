package com.developers.ecommerceapp.ekart.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.developers.ecommerceapp.ekart.dao.Cart;
import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.exceptions.CartItemNotExistException;
import com.developers.ecommerceapp.ekart.model.AddToCartDto;
import com.developers.ecommerceapp.ekart.repository.CartRepository;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
	@InjectMocks
	CartService cartService;
	@Mock
	CartRepository cartRepository;
	
	static Cart cart;
	static AddToCartDto addToCart;
	static Product product;
	static CustomerEntity customer;
	
	@BeforeAll
	public static void setUp() {
		addToCart=new AddToCartDto();
		product=new Product();
		customer=new CustomerEntity();
		
		addToCart.setId(1);
		addToCart.setProductId(2);
		addToCart.setQuantity(3);
		
		product.setId(3);
		product.setName("Product Name");
		
		customer.setCustId(1);
		customer.setEmail("abc@test.com");
		customer.setFirstName("abc");
		customer.setLastName("def");
	}
	@Test
	@DisplayName("Add To Cart: Positive Scenario")
	public void addToCartTest() {
		cart = new Cart(product, 4, customer);
		when(cartRepository.save(ArgumentMatchers.any(Cart.class))).thenReturn(cart);
		cartService.addToCart(addToCart, product, customer);
		assertNotNull(cart);
	}
	
	@Test
	@DisplayName("Add To Cart: Negative Scenario")
	public void addToCartTestNegative() {
		when(cartRepository.save(ArgumentMatchers.any(Cart.class))).thenReturn(null);
		cartService.addToCart(addToCart, product, customer);
		assertNull(null);
	}

	@Test
	@DisplayName("update Cart Item: Positive Scenario")
	public void updateCartItemTest() {
		cart = new Cart(product, 4, customer);
		when(cartRepository.save(ArgumentMatchers.any(Cart.class))).thenReturn(cart);
		when(cartRepository.getOne(addToCart.getId())).thenReturn(cart);
		cartService.updateCartItem(addToCart, customer, product);
		assertNotNull(cart);
	}

	@Test
	@DisplayName("deleteCartItem Item: Positive Scenario")
	public void deleteCartItemTest() {
		when(cartRepository.existsById(2)).thenReturn(true);
		doNothing().when(cartRepository).deleteById(2);
		cartService.deleteCartItem(2, 3);
		assertNotNull(addToCart);
	}
	
	@Test
	@DisplayName("deleteCartItem Item: Negative Scenario")
	public void deleteCartItemNegativeTest() {
		when(cartRepository.existsById(2)).thenReturn(false);
		Assertions.assertThrows(CartItemNotExistException.class, () -> {
			cartService.deleteCartItem(2, 3);
		});
	}
}
