package com.developers.ecommerceapp.ekart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

import com.developers.ecommerceapp.ekart.model.TransferBalanceRequest;

@FeignClient(url="http://localhost:8010/funds",name="fundTransfer")
public interface FeignFund {

	@PutMapping("/fundTransfer")
	public void fundTransfer(TransferBalanceRequest request);
}
