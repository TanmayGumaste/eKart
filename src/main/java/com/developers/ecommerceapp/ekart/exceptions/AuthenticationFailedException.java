package com.developers.ecommerceapp.ekart.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationFailedException extends IllegalArgumentException{
	
	private String message;
	private HttpStatus status;

}
