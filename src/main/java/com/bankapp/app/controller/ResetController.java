package com.bankapp.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.service.ResetService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ResetController {

	@Autowired
	private ResetService resetService;


	@RequestMapping("/resetPassword")
	public ResponseEntity<Object> getAccount(@RequestParam String username, @RequestParam String securityquestion1, @RequestParam String securityquestion2, @RequestParam String newPassword) {
		String result = resetService.resetPassword(username, securityquestion1, securityquestion2, newPassword);
		
		if(result.equalsIgnoreCase("Password Updated")) {
			Map<String, Object> success = new HashMap();
			success.put("status", 200);
			success.put("message", result);
			return new ResponseEntity(success, HttpStatus.OK);
		}
		else {
			Map<String, Object> error = new HashMap();
			error.put("status", 500);
			error.put("message", result);
			return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
