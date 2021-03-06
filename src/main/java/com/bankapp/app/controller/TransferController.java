package com.bankapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.Account;
import com.bankapp.app.service.HomeService;
import com.bankapp.app.service.TransferService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;
    @Autowired
    private HomeService home;

    @RequestMapping("/accountDetails")
    public ResponseEntity<Object> fetchAccountDetails(@RequestParam String accountNumber) {

        Account account = transferService.getAccount(accountNumber);

        if (account == null) {
            Map<String, Object> error = new HashMap();
            error.put("status", 500);
            error.put("message", "Invalid AccountNumber.");
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        } else {
            Map<String, Object> success = new HashMap();
            success.put("status", 200);
            success.put("account", account);
            return new ResponseEntity(success, HttpStatus.OK);
        }
    }

    @RequestMapping("/accountBalance")
    public ResponseEntity<Object> getBalance(@RequestParam String toAccountNumber, @RequestParam int amount, @RequestParam String accountNumber) {
    	
        Object result = transferService.Transfer(accountNumber, toAccountNumber, amount);

        Map<String, Object> response = new HashMap();

        if(result instanceof Error) {
            response.put("status", 500);
            response.put("message", ((Error) result).getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        response.put("status", 200);
        response.put("message", result);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
