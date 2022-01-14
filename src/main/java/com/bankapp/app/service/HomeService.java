package com.bankapp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.domain.Account;
import com.bankapp.app.domain.LoginData;
import com.bankapp.app.domain.User;
import com.bankapp.app.repository.AccountRepository;
import com.bankapp.app.repository.UserRepository;

@Service
public class HomeService {

	@Autowired
    private AccountRepository accountRepository;
	@Autowired
    private UserRepository userRepository;
	
	private Account account;
    public void homeService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository =accountRepository;
        this.userRepository =userRepository;
    }
    
    
    public String getAccountno() {
		return account.getAccountnumber();
	}
    
    public Account getMyAccount(LoginData loginData){
    	String accountNumber;
    	User user=userRepository.findByUsername(loginData.getUserName());
    	accountNumber=user.getAccountnumber();
    	account=accountRepository.findByAccountnumber(accountNumber);
    	return account;
    }
}
