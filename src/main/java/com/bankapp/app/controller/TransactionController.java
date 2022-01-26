package com.bankapp.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.Transactions;
import com.bankapp.app.service.HomeService;
import com.bankapp.app.service.TransactionsService;


@RestController
public class TransactionController {

	@Autowired
    private TransactionsService transactionService;
   @Autowired
   private HomeService homeService;
    //Transaction Handlers
    
    public void addTransactions(int amount,String senderAccountnumber,String reciverAccountnumber)
    {
        Transactions receiverTransaction=new Transactions(amount,senderAccountnumber,reciverAccountnumber);
        transactionService.saveTransactions(receiverTransaction);
        
   }
    
   
    @GetMapping(value="getTransactions")
    public ResponseEntity<Object> showAllTransactions(@RequestParam String accountNumber) {
        Map<String, Object> success = new HashMap();
        success.put("status", 200);
        success.put("transactions", transactionService.findAllTransactions(accountNumber));
        return new ResponseEntity(success, HttpStatus.OK);
    }    
}
