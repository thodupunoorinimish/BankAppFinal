package com.bankapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.Account;
import com.bankapp.app.service.HomeService;
import com.bankapp.app.service.TransferService;

@RestController
public class TransferController {

	@Autowired
    private TransferService transferService;
    @Autowired
    private HomeService home;
    
    @RequestMapping("/accountDetails")
    public ResponseEntity<Account> fetchAccountDetails(@RequestParam String accountNumber) {
        Account account=transferService.getAccount(accountNumber);
        if(account.equals(null))
        {
        	Account a=new Account();
        	a.setStatusMessage("Invalid AccountNumber");
        	return new ResponseEntity<Account>(a,HttpStatus.BAD_REQUEST);
        }
        else {
        	return new ResponseEntity<Account>(account,HttpStatus.OK);
        }
    }

    @RequestMapping("/accountBalance")
    public ResponseEntity<String> getBalance(@RequestParam String toAccountNumber, @RequestParam int amount) {
    	String fromAccountNumber= home.getAccountno();
    	String result=transferService.Transfer(fromAccountNumber, toAccountNumber, amount);
    	if(result.equalsIgnoreCase("Transfer Success")) {
        	return new ResponseEntity<String>(result,HttpStatus.OK);
    	}
    	else {
        	return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
    	}
    }
}
