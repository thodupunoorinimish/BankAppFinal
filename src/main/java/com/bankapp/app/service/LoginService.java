package com.bankapp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.domain.LoginData;
import com.bankapp.app.domain.User;
import com.bankapp.app.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
    private UserRepository userRepository;
	
	public LoginService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }
	
    public String validateLogin(LoginData loginData) {
    	boolean exists=userRepository.existsByUsername(loginData.getUserName());
        if(exists)
		{
			User user=userRepository.findByUsername(loginData.getUserName());
			if(user.getPassword().equals(loginData.getPassword())) {
            	//accountNumber=user.getAccountnumber();
            	//accountInfo=accountRepository.findByAccountnumber(accountNumber);
                return "proceed";
            }
            else {
                return "Password missmatch";
            }
		}
		else {
			return "Username does'nt exist";
		}		
    }
	
	
	
}
