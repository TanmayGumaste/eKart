package com.developers.ecommerceapp.ekart.model;

import javax.validation.constraints.NotNull;

import com.developers.ecommerceapp.ekart.dao.Cart;
import com.developers.ecommerceapp.ekart.dao.Product;

import lombok.Data;
@Data
public class CartItemDto {
	
	 private Integer id;
	    private @NotNull Integer userId;
	    private @NotNull Integer quantity;
	    private @NotNull Product product;

	    public CartItemDto() {
	    }

	    public CartItemDto(Cart cart) {
	        this.setId(cart.getId());
	        this.setUserId(cart.getCustomer().getCustId());
	        this.setQuantity(cart.getQuantity());
	        this.setProduct(cart.getProduct());
	    }

	    @Override
	    public String toString() {
	        return "CartDto{" +
	                "id=" + id +
	                ", userId=" + userId +
	                ", quantity=" + quantity +
	                ", productName=" + product.getName() +
	                '}';
	    }

}
