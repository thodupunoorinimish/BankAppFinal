package com.bankapp.app.service;

import com.bankapp.app.domain.Account;
import com.bankapp.app.domain.Requests;
import com.bankapp.app.repository.AccountRepository;
import com.bankapp.app.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private AccountRepository accountRepository;

	@Autowired
    private RequestRepository requestRepository;

    public Object getAllPendingRequests(String accountNumber) {
        Account userAccount = accountRepository.findByAccountnumber(accountNumber);

        if(userAccount == null) {
            return new Error("Account not found");
        }

        List<Requests> requests =  requestRepository.getAllPendingRequests(userAccount.getAccountnumber());
        return requests;
    }

    public Object getAllCompletedRequests(String accountNumber) {
        Account userAccount = accountRepository.findByAccountnumber(accountNumber);

        if(userAccount == null) {
            return new Error("Account not found");
        }

        List<Requests> requests =  requestRepository.getAllCompletedRequests(userAccount.getAccountnumber());
        return requests;
    }

    public Requests getRequest(int requestId) {
        return requestRepository.getRequestDetails(requestId);
    }

    public void setRequestPaid(Requests request) {
        request.setStatus(true);
        requestRepository.save(request);
    }

}
