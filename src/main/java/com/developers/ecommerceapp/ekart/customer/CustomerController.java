package com.developers.ecommerceapp.ekart.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developers.ecommerceapp.ekart.exceptions.CustomException;
import com.developers.ecommerceapp.ekart.model.Customer;
import com.developers.ecommerceapp.ekart.model.Response;
import com.developers.ecommerceapp.ekart.model.SignIn;
import com.developers.ecommerceapp.ekart.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	@PostMapping("/signup")
	public ResponseEntity<?> customerSignUp(@RequestBody Customer customerDetails) throws CustomException {
		logger.info("CustomerController : createUser : Start");
		boolean isSaved = customerService.saveUserDetails(customerDetails);
		logger.info("CustomerController : createUser : End");
		if (isSaved)
			
			return new ResponseEntity<>(new Response("Account is Created"),HttpStatus.OK);
		 return new ResponseEntity<>(new Response("Registration Failed"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody SignIn signIn) {
		boolean isPresent=customerService.AuthenticateUser(signIn);
		if(isPresent)
			return new ResponseEntity<>(new Response("Login Success.."),HttpStatus.OK);
		 return new ResponseEntity<>(new Response("login failed"),HttpStatus.UNAUTHORIZED);
	}

}
