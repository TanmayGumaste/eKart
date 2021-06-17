package com.developers.ecommerceapp.ekart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.developers.ecommerceapp.ekart.dao.CustomerEntity;
import com.developers.ecommerceapp.ekart.exceptions.AuthenticationFailedException;
import com.developers.ecommerceapp.ekart.exceptions.CustomException;
import com.developers.ecommerceapp.ekart.model.Customer;
import com.developers.ecommerceapp.ekart.model.SignIn;
import com.developers.ecommerceapp.ekart.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	CustomerRepository customerRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.customerRepository = customerRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public boolean saveUserDetails(Customer userRequestDto)  throws CustomException {
		CustomerEntity customerEntity=new CustomerEntity();
		if(null!= customerRepository.findByEmail(userRequestDto.getEmail()))
		throw new CustomException("User already exists");
		customerEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
		BeanUtils.copyProperties(userRequestDto, customerEntity);
		CustomerEntity custPersist = customerRepository.save(customerEntity);
		if(ObjectUtils.isEmpty(custPersist))return false;
		return true;
	}

	@Override
	public boolean AuthenticateUser(SignIn signIn) throws AuthenticationFailedException {
		CustomerEntity customerEntity=customerRepository.findByEmail(signIn.getEmail());
		if(ObjectUtils.isEmpty(customerEntity))throw new AuthenticationFailedException("User Not Found,Please Sign Up",HttpStatus.NOT_FOUND);
		boolean flag=bCryptPasswordEncoder.matches(signIn.getPassword(), customerEntity.getEncryptedPassword());
		if(flag) {
			return true;
		}else {
			throw new AuthenticationFailedException("password is incorrect",HttpStatus.UNAUTHORIZED);
		}
	}

}
