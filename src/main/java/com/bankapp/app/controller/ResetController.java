package com.bankapp.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bankapp.app.service.ResetService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ResetController {

	@Autowired
	private ResetService resetService;


	@RequestMapping("/resetPassword")
	public ResponseEntity<Object> getAccount(@RequestBody ForgotPasswordInput data) {

		String result = resetService.resetPassword(data.username, data.securityquestion1, data.securityquestion2, data.newPassword);
		
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


class ForgotPasswordInput {
	String username;
	String securityquestion1;
	String securityquestion2;
	String newPassword;

	@Override
	public String toString() {
		return "ForgotPasswordInput{" +
				"username='" + username + '\'' +
				", securityquestion1='" + securityquestion1 + '\'' +
				", securityquestion2='" + securityquestion2 + '\'' +
				", newPassword='" + newPassword + '\'' +
				'}';
	}

	public ForgotPasswordInput(String username, String securityquestion1, String securityquestion2, String newPassword) {
		this.username = username;
		this.securityquestion1 = securityquestion1;
		this.securityquestion2 = securityquestion2;
		this.newPassword = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public String getSecurityquestion1() {
		return securityquestion1;
	}

	public String getSecurityquestion2() {
		return securityquestion2;
	}

	public String getNewPassword() {
		return newPassword;
	}
}
