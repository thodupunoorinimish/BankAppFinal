package com.bankapp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.controller.TransactionController;
import com.bankapp.app.domain.Account;
import com.bankapp.app.repository.AccountRepository;

@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionController transController;

    public Account getAccount(String accountNumber) {
        Account account = accountRepository.findByAccountnumber(accountNumber);
        return account;
    }

    public Object Transfer(String fromAccountNumber, String toAccountNumber, int amount) {
        Account senderAccount = accountRepository.findByAccountnumber(fromAccountNumber);
        Account receiverAccount = accountRepository.findByAccountnumber(toAccountNumber);

        if (receiverAccount == null) {
            return new Error("Account Not Found");
        }

        if (senderAccount.getAccountnumber() == receiverAccount.getAccountnumber()) {
            return new Error("Transferring to same account!");
        }

        if (senderAccount.getBalance() < amount) {
            return new Error("Insufficient funds");
        }
        if (senderAccount.getSetLimit() < amount) {
            return new Error("Limit Exceeded");
        }
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        accountRepository.save(receiverAccount);
        accountRepository.save(senderAccount);
        transController.addTransactions(amount, fromAccountNumber, toAccountNumber);
        return "Transfer Success";
    }
}
