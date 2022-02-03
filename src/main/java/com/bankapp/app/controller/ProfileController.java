package com.bankapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.domain.PasswordUpdate;
import com.bankapp.app.domain.User;
import com.bankapp.app.service.HomeService;
import com.bankapp.app.service.ProfileService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping("/getUser")
    public ResponseEntity<Object> getUser(@RequestBody getUserRequestData body) {

        if (body.getAccountNumber() == null) {
            Map<String, Object> error = new HashMap();
            error.put("status", 500);
            error.put("message", "accountNumber is required.");
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        User user = profileService.getUser(body.getAccountNumber());

        if (user == null) {
            Map<String, Object> error = new HashMap();
            error.put("status", 500);
            error.put("message", "User not found");
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> success = new HashMap();
        success.put("status", 200);
        success.put("user", user);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @RequestMapping("/updatePassword")
    public ResponseEntity<Object> updatePassword(@RequestBody PasswordUpdate body) {

        if (body.getAccountNumber() == null) {
            Map<String, Object> error = new HashMap();
            error.put("status", 500);
            error.put("message", "accountNumber is required.");
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        if (body.getNewPassword() == null) {
            Map<String, Object> error = new HashMap();
            error.put("status", 500);
            error.put("message", "newPassword is required.");
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        if (body.getCurrentPassword() == null) {
            Map<String, Object> error = new HashMap();
            error.put("status", 500);
            error.put("message", "currentPassword is required.");
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        String result = profileService.updatePassword(body.getAccountNumber(), body.getCurrentPassword(), body.getNewPassword());

        if (result.equalsIgnoreCase("Password Updated")) {
            Map<String, Object> success = new HashMap();
            success.put("status", 200);
            success.put("message", result);
            return new ResponseEntity(success, HttpStatus.OK);
        } else {
            Map<String, Object> error = new HashMap();
            error.put("status", 500);
            error.put("message", result);
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping("/setLimit")
    public String transactionLimit(@RequestParam int limitSet, @RequestParam String accnum){
        return profileService.transactionLimit(limitSet, accnum);
    }
}

class getUserRequestData {

    String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "getUserRequestData{" +
                "accountNumber='" + accountNumber + '\'' +
                '}';
    }
}