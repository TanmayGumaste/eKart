package com.developers.ecommerceapp.ekart.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.developers.ecommerceapp.ekart.model.Customer;
import com.developers.ecommerceapp.ekart.model.SignIn;
import com.developers.ecommerceapp.ekart.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
	
	@Mock
	CustomerService customerService;
	
	@InjectMocks
	CustomerController customerController;
	
	static Customer customer;
	static SignIn signIn;
	

	@BeforeAll
	public static void setUp() {
		
		customer=new Customer();
		signIn=new SignIn();
		customer.setFirstName("abc");
		customer.setLastName("def");
		customer.setEmail("abc@test.com");
		customer.setPassword("12344");
		customer.setContactno("1234567890");
		customer.setAddress("Malad");
		signIn.setEmail("abc@gmail.com");
		signIn.setPassword("1234567890");
	}
	@Test
	@DisplayName("create customer: Positive Scenario")
	public void customerSignUpPositive() {
		when(customerService.saveUserDetails(customer)).thenReturn(true);
		ResponseEntity<?> response = customerController.customerSignUp(customer);
		assertEquals(200, response.getStatusCodeValue());
	}
	@Test
	@DisplayName("create customer: Negative Scenario")
	public void customerSignUpNegative() {
		when(customerService.saveUserDetails(customer)).thenReturn(false);
		ResponseEntity<?> response = customerController.customerSignUp(customer);
		assertEquals(500, response.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("Login Function: Positive Scenario")
	public void loginTestPositive() {
		when(customerService.AuthenticateUser(signIn)).thenReturn(true);
		ResponseEntity<?> response = customerController.login(signIn);
		assertEquals(200,response.getStatusCodeValue());
	}
	
	@Test
	@DisplayName("Login Function: Negative Scenario")
	public void loginTestNegative() {
		when(customerService.AuthenticateUser(signIn)).thenReturn(false);
		ResponseEntity<?> response = customerController.login(signIn);
		assertEquals(401,response.getStatusCodeValue());
	}
}
