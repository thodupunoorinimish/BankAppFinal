package com.bankapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.Account;
import com.bankapp.app.domain.LoginData;
import com.bankapp.app.service.HomeService;
import com.bankapp.app.service.LoginService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

	@Autowired
    private LoginService loginService;
	@Autowired
	private HomeService homeService;
	
	@PostMapping("/login")
    public ResponseEntity<Account> checkLogin(@RequestBody LoginData loginData) {
		
		Account account;
		
		String statusMessage=loginService.validateLogin(loginData);
		
		if(statusMessage.equalsIgnoreCase("proceed"))
		{
			account=homeService.getMyAccount(loginData);
			return new ResponseEntity<Account>(account,HttpStatus.OK);
		}
		else {
			account=new Account();
			account.setStatusMessage(statusMessage);
			return new ResponseEntity<Account>(account,HttpStatus.BAD_REQUEST);
		}  
    }
}


//"accountnumber":"123456789",
//"userName":"Praveen",
//"password":"mnbvcxzasd",
//"firstname":"sdfgsdfag",
//"lastname":"fgsd",
//"phonenumber":"afsdgsdar"