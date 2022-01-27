package com.bankapp.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.Account;
import com.bankapp.app.domain.Beneficiary;
import com.bankapp.app.service.BeneficiaryService;



@RestController
@CrossOrigin("*")
public class BeneficiaryController {
	@Autowired
	private BeneficiaryService bene_service;
	
	
	@PostMapping(value="addBeneficiaryList")
	public ResponseEntity<String> addBeneficiaryList(@RequestBody Beneficiary beneficiary)
	{
		Account account= bene_service.getAccount(beneficiary.getReceiverAccNo());
		String response="";
		if(account==null) {
			response="Account Not Found";
			return new ResponseEntity<String>(response,HttpStatus.BAD_REQUEST);
		} 
		else if(bene_service.existsByReceiverAccNo(beneficiary.getReceiverAccNo())==false) {
			bene_service.addBeneficiaryList(beneficiary);
			response= "Beneficiary Added";
			return new ResponseEntity<String>(response,HttpStatus.OK);
			}
		else {
				response= "Beneficiary already exists";
				return new ResponseEntity<String>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="getBeneficiaryList")
	public ResponseEntity<Object> getBeneficiaryList(@RequestParam String myAccountNumber)
	{
		Map<String, Object> map= new HashMap();
		map.put("status", 200);
		map.put("beneficiary", bene_service.getBeneficiaryListByAccountNumber(myAccountNumber));
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	
}
