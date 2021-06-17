package com.developers.ecommerceapp.ekart.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developers.ecommerceapp.ekart.dao.Order;
import com.developers.ecommerceapp.ekart.exceptions.ProductNotExistException;
import com.developers.ecommerceapp.ekart.model.ApiResponse;
import com.developers.ecommerceapp.ekart.service.OrderService;
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @PostMapping("/checkout/{id}")
    public ResponseEntity<ApiResponse> checkOut(@PathVariable("id") Integer cust_Id) throws  ProductNotExistException {
    	Order order = orderService.checkOut(cust_Id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Order placed successfully",order.getId()), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderHistory(@PathVariable("id") Integer cust_Id)  { 
    List<Order> orderDtoList = orderService.listOrders(cust_Id);
    return new ResponseEntity<List<Order>>(orderDtoList,HttpStatus.OK);}
}
