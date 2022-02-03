package com.bankapp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.domain.User;
import com.bankapp.app.repository.UserRepository;

@Service
public class ProfileService {
	@Autowired
	private UserRepository userRepository;
	public User getUser(String accountNumber) {
        User user=userRepository.findByAccountnumber(accountNumber);
        return user;
    }
   
    public String updatePassword(String accountNumber,String currentPassword, String newPassword) {
        User user=userRepository.findByAccountnumber(accountNumber);
        if(currentPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return "Password Updated";
        }
        return "Current Password is not valid";
    }

    public String transactionLimit(int setLimit, String accountNumber) {
        Account account = accountRepository.findByAccountnumber(accountNumber);
        account.setSetlimit(setLimit);
        accountRepository.save(account);
        return "limit seted";
    }

}