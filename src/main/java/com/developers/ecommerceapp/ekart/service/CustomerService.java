package com.developers.ecommerceapp.ekart.service;

import com.developers.ecommerceapp.ekart.exceptions.AuthenticationFailedException;
import com.developers.ecommerceapp.ekart.exceptions.CustomException;
import com.developers.ecommerceapp.ekart.model.Customer;
import com.developers.ecommerceapp.ekart.model.SignIn;

public interface CustomerService {
	
	boolean saveUserDetails(Customer userRequestDto)  throws CustomException;
	boolean AuthenticateUser(SignIn signIn) throws AuthenticationFailedException;

}
