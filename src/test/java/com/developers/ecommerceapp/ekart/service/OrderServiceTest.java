package com.developers.ecommerceapp.ekart.service;

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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;

import com.developers.ecommerceapp.ekart.dao.Cart;
import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.dao.Order;
import com.developers.ecommerceapp.ekart.dao.Product;
import com.developers.ecommerceapp.ekart.feign.FeignFund;
import com.developers.ecommerceapp.ekart.model.AddToCartDto;
import com.developers.ecommerceapp.ekart.model.CartDto;
import com.developers.ecommerceapp.ekart.model.CartItemDto;
import com.developers.ecommerceapp.ekart.model.PlaceOrderDto;
import com.developers.ecommerceapp.ekart.repository.CustomerRepository;
import com.developers.ecommerceapp.ekart.repository.OrderItemsRepository;
import com.developers.ecommerceapp.ekart.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@InjectMocks
	OrderService orderService;
	
	@Mock
	CartService cartService;
	
	@Mock
	CustomerRepository custRepo;
	
	@Mock
	OrderRepository orderRepo;
	
	@Mock
	OrderItemsService orderItemsService;
	
	@Mock
	FeignFund fund;
	
	@Mock
	CircuitBreakerFactory circuitBreakerFactory;
	
	 @Mock
	 OrderItemsRepository orderItemsRepository;
	
	static Cart cart;
	static AddToCartDto addToCart;
	static Product product;
	static CustomerEntity customer;
	static CartDto cartDto;
	static CartItemDto cartItem;
	
	@BeforeAll
	public static void setUp() {
		addToCart=new AddToCartDto();
		product=new Product();
		customer=new CustomerEntity();
		cartItem=new CartItemDto();
		cartItem.setId(1);
		cartItem.setQuantity(2);
		cartItem.setProduct(product);
		cartItem.setUserId(1);
		List<CartItemDto> cartList= new ArrayList<CartItemDto>();
		cartList.add(cartItem);
		cartDto=new CartDto(cartList, 10.0);
		customer.setEmail("test@gmail.com");
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
	@DisplayName("checkout: Positive Scenario")
	public void checkoutTest() {
		when(custRepo.findByCustId(1)).thenReturn(customer);
		when(cartService.listCartItems(customer)).thenReturn(cartDto);
		doNothing().when(cartService).deleteUserCartItems(Mockito.any());
		when(orderRepo.save(Mockito.any())).thenReturn(new Order());
		doNothing().when(fund).fundTransfer(Mockito.any());
		assertNotNull(orderService.checkOut(1));
	}

	@Test
	@DisplayName("saveOrder: Positive Scenario")
	public void saveOrderTest() {
		when(orderRepo.save(Mockito.any())).thenReturn(new Order());
		assertNotNull(orderService.saveOrder(new PlaceOrderDto(), customer));
	}

	@Test
	@DisplayName("listOrders: Positive Scenario")
	public void listOrdersTest() {
		when(orderRepo.findAllByUserOrderByCreatedDateDesc(Mockito.any())).thenReturn(new ArrayList<Order>());
		assertNotNull(orderService.listOrders(1));
	}

//	@Test
//	@DisplayName("deleteCartItem Item: Negative Scenario")
//	public void deleteCartItemNegativeTest() {
//		when(cartRepository.existsById(2)).thenReturn(false);
//		Assertions.assertThrows(CartItemNotExistException.class, () -> {
//			cartService.deleteCartItem(2, 3);
//		});
//	}
}
