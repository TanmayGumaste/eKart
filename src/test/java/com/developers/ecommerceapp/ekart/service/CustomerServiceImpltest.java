package com.developers.ecommerceapp.ekart.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.exceptions.AuthenticationFailedException;
import com.developers.ecommerceapp.ekart.exceptions.CustomException;
import com.developers.ecommerceapp.ekart.model.Customer;
import com.developers.ecommerceapp.ekart.model.SignIn;
import com.developers.ecommerceapp.ekart.repository.CustomerRepository;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceImpltest {
	
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	@Mock
	CustomerRepository customerRepository;

	static BCryptPasswordEncoder bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
	
	static Customer customer;
	static SignIn signIn;
	static CustomerEntity customerEntity;
	

	@BeforeAll
	public static void setUp() {
		
		customer=new Customer();
		signIn=new SignIn();
		customerEntity=new CustomerEntity();
		customer.setFirstName("abc");
		customer.setLastName("def");
		customer.setEmail("abc@test.com");
		customer.setPassword("12344");
		customer.setContactno("1234567890");
		customer.setAddress("Malad");
		signIn.setEmail("abc@test.com");
		signIn.setPassword("1234567890");
		customerEntity.setCustId(1);
		customerEntity.setFirstName("abc");
		customerEntity.setLastName("def");
		customerEntity.setEmail("abc@test.com");
		customerEntity.setEncryptedPassword("hashedPassword");
		customerEntity.setContactno("1234567890");
		customerEntity.setAddress("Malad");
		when(bCryptPasswordEncoder.encode(customer.getPassword())).thenReturn("hashedPassword");
	}
	
	@Test
	@DisplayName("saveUserDetails: Positive Scenario")
	public void saveUserDetailsPositive() {
		when(customerRepository.findByEmail("abc@test.com")).thenReturn(null);
		when(customerRepository.save(ArgumentMatchers.any(CustomerEntity.class))).thenReturn(customerEntity);
		boolean saveUserDetails=customerServiceImpl.saveUserDetails(customer);
		assertTrue(saveUserDetails);
	}
	
	@Test
	@DisplayName("UserAlready Exists: Negative Scenario")
	public void saveUserDetailsExits() {
		when(customerRepository.findByEmail("abc@test.com")).thenReturn(customerEntity);
		Assertions.assertThrows(CustomException.class, () -> {
			customerServiceImpl.saveUserDetails(customer);
		});
	}
	
	@Test
	@DisplayName("saveUserDetails: Negative Scenario Save Failed")
	public void saveUserDetailsNegativeSaveFailed() {
		//when(customerRepository.save(customerEntity))
		when(customerRepository.findByEmail("abc@test.com")).thenReturn(null);
		when(customerRepository.save(ArgumentMatchers.any(CustomerEntity.class))).thenReturn(null);
		boolean saveUserDetails=customerServiceImpl.saveUserDetails(customer);
		assertFalse(saveUserDetails);
	}

	@Test
	@DisplayName("login: Positive Scenario")
	public void loginTest() {
	when(customerRepository.findByEmail("abc@test.com")).thenReturn(customerEntity);
	when(bCryptPasswordEncoder.matches(signIn.getPassword(), customerEntity.getEncryptedPassword())).thenReturn(true);
	assertTrue(customerServiceImpl.AuthenticateUser(signIn));
	}
	@Test
	@DisplayName("login: Negative Scenario")
	public void loginTestNegative() {
	when(customerRepository.findByEmail("abc@test.com")).thenReturn(customerEntity);
	when(bCryptPasswordEncoder.matches(signIn.getPassword(), customerEntity.getEncryptedPassword())).thenReturn(false);
	Assertions.assertThrows(AuthenticationFailedException.class, () -> {
		customerServiceImpl.AuthenticateUser(signIn);
	});
	}
	
	@Test
	@DisplayName("login User Not Found: Negative Scenario")
	public void loginTestNegativeUserNotFound() {
	when(customerRepository.findByEmail("abc@test.com")).thenReturn(null);
	Assertions.assertThrows(AuthenticationFailedException.class, () -> {
		customerServiceImpl.AuthenticateUser(signIn);
	});
	}

}
