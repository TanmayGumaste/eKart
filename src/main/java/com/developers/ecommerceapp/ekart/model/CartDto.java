package com.developers.ecommerceapp.ekart.model;

import java.util.List;

import lombok.Data;

@Data
public class CartDto {
	
	 private List<CartItemDto> cartItems;
	    private double totalCost;

	    public CartDto(List<CartItemDto> cartItemDtoList, double totalCost) {
	        this.cartItems = cartItemDtoList;
	        this.totalCost = totalCost;
	    }

}
