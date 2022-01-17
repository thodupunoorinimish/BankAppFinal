package com.bankapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.PasswordUpdate;
import com.bankapp.app.domain.User;
import com.bankapp.app.service.HomeService;
import com.bankapp.app.service.ProfileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	@Autowired
	private HomeService homeService;
	
	
	@RequestMapping("/getUser")
    public ResponseEntity<User> getUser() {
		String accountNumber=homeService.getAccountno();
        User user=profileService.getUser(accountNumber);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
   
    @RequestMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdate passwordUpdate) {
    	String accountNumber=homeService.getAccountno();
    	String currentPassword=passwordUpdate.getCurrentPassword();
    	String newPassword=passwordUpdate.getNewPassword();
        String result=profileService.updatePassword(accountNumber,currentPassword,newPassword);
        if(result.equalsIgnoreCase("Password Updated")) {
        	return new ResponseEntity<String>(result,HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
        }
    }
}
