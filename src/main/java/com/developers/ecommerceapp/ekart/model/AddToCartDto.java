package com.developers.ecommerceapp.ekart.model;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class AddToCartDto {
	
	    private Integer id;
	    private @NotNull Integer productId;
	    private @NotNull Integer quantity;

	    public AddToCartDto() {
	    }
	    @Override
	    public String toString() {
	        return "CartDto{" +
	                "id=" + id +
	                ", productId=" + productId +
	                ", quantity=" + quantity +
	                ",";
	    }

}
