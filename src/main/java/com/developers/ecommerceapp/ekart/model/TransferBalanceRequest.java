package com.developers.ecommerceapp.ekart.model;

import lombok.Data;

@Data
public class TransferBalanceRequest {
	
	private String email;

    private String toName;

    private Double amount;
    
    private int accountNo;

}
