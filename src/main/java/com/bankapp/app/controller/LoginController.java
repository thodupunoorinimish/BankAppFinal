package com.bankapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.Account;
import com.bankapp.app.domain.LoginData;
import com.bankapp.app.service.HomeService;
import com.bankapp.app.service.LoginService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private HomeService homeService;

	@PostMapping("/login")
	public ResponseEntity<Object> checkLogin(@RequestBody LoginData loginData) {

		String statusMessage=loginService.validateLogin(loginData);

		if(statusMessage.equalsIgnoreCase("proceed"))
		{
			Map<String, Object> success = new HashMap();
			success.put("status", 200);
			success.put("account", homeService.getMyAccount(loginData));
			return new ResponseEntity(success, HttpStatus.OK);
		}
		else {
			Map<String, Object> error = new HashMap();
			error.put("status", 500);
			error.put("message", statusMessage);
			return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
		}
	}
}


//"accountnumber":"123456789",
//"userName":"Praveen",
//"password":"mnbvcxzasd",
//"firstname":"sdfgsdfag",
//"lastname":"fgsd",
//"phonenumber":"afsdgsdar"