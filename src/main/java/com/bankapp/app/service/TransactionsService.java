package com.bankapp.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.domain.Transactions;
import com.bankapp.app.repository.TransactionRepository;

@Service
public class TransactionsService {
	@Autowired
    private TransactionRepository transactionRepository;

 

    public Transactions saveTransactions(Transactions transaction) {
        return transactionRepository.save(transaction);
    }

 

    public List<Transactions> findAllTransactions(String accountnumber) {
    	List<Transactions> transactionList=new ArrayList<Transactions>();
    	transactionList=transactionRepository.findByAccountNumber1(accountnumber);
    	for(Transactions t:transactionList) {
    		t.setType("Debit");
    	}
    	List<Transactions> transactionList2=new ArrayList<Transactions>();
    	transactionList2=transactionRepository.findByAccountNumber2(accountnumber);
    	for(Transactions t:transactionList2) {
    		t.setType("Credit");
    	}
    	transactionList.addAll(transactionList2);
        return transactionList;
    }
}
