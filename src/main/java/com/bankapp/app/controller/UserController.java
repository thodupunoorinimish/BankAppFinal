package com.bankapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.User;
import com.bankapp.app.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<String> userSave(@RequestBody User user) {
		String accountNumber=userService.userDetails(user);
		if(accountNumber.equalsIgnoreCase("Invalid"))
		{
			return new ResponseEntity<String>("Phonenumber not registered",HttpStatus.BAD_REQUEST);
		}
		else {
			user.setAccountnumber(accountNumber);
			String status=userService.checkUser(user);
			if(status.equalsIgnoreCase("proceed")) {
//				status=userService.checkAccount(user);
//				if(status.equalsIgnoreCase("proceed"))
//				{
					userService.saveUser(user);
					return new ResponseEntity<String>("User Added Successfully",HttpStatus.CREATED);
//				}
//				else {
//					return new ResponseEntity<String>(status,HttpStatus.BAD_REQUEST);
//				}
			}
			else {
				return new ResponseEntity<String>(status,HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*@RequestMapping("/login")
	public User userlogin(@RequestParam int username,@RequestParam String password) {
		
		int uname=username;
		String paswrd=password;
		userService.checklogin(uname, paswrd);
		return null;
		
	}*/
}
