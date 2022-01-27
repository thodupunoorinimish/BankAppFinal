package com.bankapp.app.controller;

import com.bankapp.app.domain.Requests;
import com.bankapp.app.service.RequestService;
import com.bankapp.app.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private TransferService transferService;

    @GetMapping(value = "requests/pending")
    public ResponseEntity<Object> getAllPendingRequests(@RequestParam String accountNumber) {

        Object data = requestService.getAllPendingRequests(accountNumber);

        if (data instanceof String) {
            Map<String, Object> error = new HashMap();
            error.put("status", 500);
            error.put("message", data);
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> success = new HashMap();
        success.put("status", 200);
        success.put("requests", data);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping(value = "requests/completed")
    public ResponseEntity<Object> getAllCompletedRequests(@RequestParam String accountNumber) {

        Object data = requestService.getAllCompletedRequests(accountNumber);

        if (data instanceof Error) {
            Map<String, Object> error = new HashMap();
            error.put("status", 500);
            error.put("message", ((Error) data).getMessage());
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> success = new HashMap();
        success.put("status", 200);
        success.put("requests", data);
        return new ResponseEntity(success, HttpStatus.OK);
    }


    @PostMapping(value = "requests/pay")
    public ResponseEntity<Object> payRequest(@RequestBody RequestPayInput body) {

        Requests request = requestService.getRequest(body.getRequestId());

        Map<String, Object> response = new HashMap();

        if(request == null) {
            response.put("status", 500);
            response.put("message", "Request not found.");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        if(body.getAccountNumber() != request.getToAccount()) {
            response.put("status", 500);
            response.put("message", "Access denied.");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        if(request.getStatus() == true) {
            response.put("status", 500);
            response.put("message", "Request already paid.");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        Object transferStatus = transferService.Transfer(request.getToAccount(), request.getFromAccount(), request.getAmount());

        if(transferStatus instanceof Error) {
            response.put("status", 500);
            response.put("message", ((Error) transferStatus).getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        requestService.setRequestPaid(request);

        response.put("status", 200);
        response.put("message", "Request Paid.");
        return new ResponseEntity(response, HttpStatus.OK);
    }

}

class RequestPayInput {
    String accountNumber;
    int requestId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getRequestId() {
        return requestId;
    }

    @Override
    public String toString() {
        return "RequestPayInput{" +
                "accountNumber='" + accountNumber + '\'' +
                ", requestId=" + requestId +
                '}';
    }
}