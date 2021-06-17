package com.developers.ecommerceapp.ekart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.dao.Order;
import com.developers.ecommerceapp.ekart.dao.OrderItem;
import com.developers.ecommerceapp.ekart.exceptions.CartItemNotExistException;
import com.developers.ecommerceapp.ekart.feign.FeignFund;
import com.developers.ecommerceapp.ekart.model.CartDto;
import com.developers.ecommerceapp.ekart.model.CartItemDto;
import com.developers.ecommerceapp.ekart.model.PlaceOrderDto;
import com.developers.ecommerceapp.ekart.model.TransferBalanceRequest;
import com.developers.ecommerceapp.ekart.repository.CustomerRepository;
import com.developers.ecommerceapp.ekart.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	OrderItemsService orderItemsService;
	
	@Autowired
	FeignFund fund;
	
	@Autowired
	CircuitBreakerFactory circuitBreakerFactory;
	
	public Order checkOut(Integer cust_Id) {

		CustomerEntity cust = custRepo.findByCustId(cust_Id);
        CartDto cartDto = cartService.listCartItems(cust);
        CircuitBreaker circuitBreaker=circuitBreakerFactory.create("fundTransfer");

        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        placeOrderDto.setUser(cust);
        placeOrderDto.setTotalPrice(cartDto.getTotalCost());

        Order newOrder = saveOrder(placeOrderDto, cust);
        TransferBalanceRequest request = new TransferBalanceRequest();
        request.setAmount(cartDto.getTotalCost());
        request.setEmail(cust.getEmail());
        request.setAccountNo(752);
        request.setToName("Ekart");
        fund.fundTransfer(request);
        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            OrderItem orderItem = new OrderItem(
                    newOrder,
                    cartItemDto.getProduct(),
                    cartItemDto.getQuantity(),
                    cartItemDto.getProduct().getPrice());
            orderItemsService.addOrderedProducts(orderItem);
        }
        cartService.deleteUserCartItems(cust);
        return newOrder;
    	
	}
	
	   public Order saveOrder(PlaceOrderDto orderDto, CustomerEntity user){
	        Order order = getOrderFromDto(orderDto, user);
	        return orderRepo.save(order);
	    }

	    private Order getOrderFromDto(PlaceOrderDto orderDto, CustomerEntity user) {
	        Order order = new Order(orderDto, user);
	        return order;
	    }
	    
	    public List<Order> listOrders(Integer userId) {
	    	CustomerEntity user = custRepo.findByCustId(userId);
	        List<Order> orderList = orderRepo.findAllByUserOrderByCreatedDateDesc(user);
	        return orderList;
	    }
	    private String getDefaultInfo() {
			return "Fund Service is Down,please try aftersome time";
		}
}
