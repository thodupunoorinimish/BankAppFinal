package com.bankapp.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.domain.Account;
import com.bankapp.app.domain.User;
import com.bankapp.app.repository.AccountRepository;
import com.bankapp.app.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	public UserService(UserRepository userRepository, AccountRepository accountRepository)
	{
		this.userRepository=userRepository;
		this.accountRepository=accountRepository;
	}

	public void saveUser(User user)
	{
		userRepository.save(user);
	}
	
	public String checkUser(User user) {
		
		if(userRepository.existsByAccountnumber(user.getAccountnumber()))
		{
			return "Account already registered";
		}
		else if(userRepository.existsByUsername(user.getUsername())) {
			return "Username already registered";
		}
		else {
			return "proceed";
		}
	}
	
	public String userDetails(User user) {
		if(accountRepository.existsByPhonenumber(user.getPhonenumber())) {
			Account a=accountRepository.findByPhonenumber(user.getPhonenumber());
			String accountNumber=a.getAccountnumber();
			return accountNumber;
		}
		return "Invalid";
	}
	
//	public String checkAccount(User user) {
//		
//			Account account=accountRepository.findByAccountnumber(user.getAccountnumber());
//			if(account.getPhonenumber().equals(user.getPhonenumber()))
//			{
//				return "proceed";
//			}
//			else
//			{
//				return "Phone number doesnt match";
//			}
//		}	
	
}
