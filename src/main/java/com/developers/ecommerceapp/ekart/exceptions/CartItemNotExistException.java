package com.developers.ecommerceapp.ekart.exceptions;

public class CartItemNotExistException extends IllegalArgumentException {
    public CartItemNotExistException(String msg) {
        super(msg);
    }
    public CartItemNotExistException() {
    	
    }
}
