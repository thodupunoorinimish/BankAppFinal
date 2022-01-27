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

    @PostMapping(value = "requests/create")
    public ResponseEntity<Object> createRequest(@RequestBody Requests body) {

        Map<String, Object> response = new HashMap();

        try {

            if (body.getFromAccount() == null) {
                throw new Exception("fromAccount is required");
            }

            if (body.getToAccount() == null) {
                throw new Exception("toAccount is required");
            }

            if (body.getAmount() == 0) {
                throw new Exception("amount is required");
            }

            requestService.createRequest(body);

            response.put("status", 200);
            response.put("message", "Request created");
            return new ResponseEntity(response, HttpStatus.OK);

        } catch (Exception exception) {
            response.put("status", 500);
            response.put("message", exception.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "requests/pending")
    public ResponseEntity<Object> getAllPendingRequests(@RequestParam String accountNumber) {

        Object data = requestService.getAllPendingRequests(accountNumber);

        Map<String, Object> response = new HashMap();

        if (data instanceof String) {
            response.put("status", 500);
            response.put("message", data);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> success = new HashMap();
        response.put("status", 200);
        response.put("requests", data);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "requests/completed")
    public ResponseEntity<Object> getAllCompletedRequests(@RequestParam String accountNumber) {

        Object data = requestService.getAllCompletedRequests(accountNumber);

        Map<String, Object> response = new HashMap();

        if (data instanceof Error) {
            response.put("status", 500);
            response.put("message", ((Error) data).getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        response.put("status", 200);
        response.put("requests", data);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @PostMapping(value = "requests/pay")
    public ResponseEntity<Object> payRequest(@RequestBody RequestPayInput body) {

        Requests request = requestService.getRequest(body.getRequestId());

        Map<String, Object> response = new HashMap();

        if (request == null) {
            response.put("status", 500);
            response.put("message", "Request not found.");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        if (body.getAccountNumber() != request.getToAccount()) {
            response.put("status", 500);
            response.put("message", "Access denied.");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        if (request.getStatus() == true) {
            response.put("status", 500);
            response.put("message", "Request already paid.");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        Object transferStatus = transferService.Transfer(request.getToAccount(), request.getFromAccount(), request.getAmount());

        if (transferStatus instanceof Error) {
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