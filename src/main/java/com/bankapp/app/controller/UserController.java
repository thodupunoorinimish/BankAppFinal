package com.bankapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.User;
import com.bankapp.app.service.UserService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<Object> userSave(@RequestBody User user) {

		String accountNumber=userService.userDetails(user);

		if(accountNumber.equalsIgnoreCase("Invalid"))
		{
			Map<String, Object> error = new HashMap();
			error.put("status", 500);
			error.put("message", "Phone number is not registered.");
			return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
		}
		else {
			user.setAccountnumber(accountNumber);
			String status=userService.checkUser(user);
			if(status.equalsIgnoreCase("proceed")) {
				userService.saveUser(user);

				Map<String, Object> success = new HashMap();
				success.put("status", 200);
				success.put("message", "User Added Successfully");
				return new ResponseEntity(success, HttpStatus.CREATED);
			}
			else {
				Map<String, Object> error = new HashMap();
				error.put("status", 500);
				error.put("message", status);
				return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
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
