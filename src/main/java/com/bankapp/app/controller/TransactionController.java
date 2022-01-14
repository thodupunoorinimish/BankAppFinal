package com.bankapp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Transactions> showAllTransactions(){
    	String accountNumber=homeService.getAccountno();
        return transactionService.findAllTransactions(accountNumber);
    }    
}
