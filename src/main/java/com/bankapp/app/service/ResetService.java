package com.bankapp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.domain.User;
import com.bankapp.app.repository.UserRepository;


@Service
public class ResetService {
	
	
	@Autowired
	private UserRepository userRepos;
		
	private User user;
	

	public String resetPassword(String username, String securityquestion1, String securityquestion2, String newPassword) {
		user = userRepos.findByUser(username);
		if(user != null) {
			if(securityquestion1.equalsIgnoreCase(user.getSecurityquestion1())) {
				if(securityquestion2.equalsIgnoreCase(user.getSecurityquestion2())) {
					user.setPassword(newPassword);
					userRepos.save(user);
					return "Password Updated";
				}
			}
			return "Credentials incorrect";
		}
		return "Credentials incorrect";
	}	

}
