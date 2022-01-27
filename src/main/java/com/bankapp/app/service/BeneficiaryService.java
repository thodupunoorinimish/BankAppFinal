package com.bankapp.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.domain.Account;
import com.bankapp.app.domain.Beneficiary;
import com.bankapp.app.repository.AccountRepository;
import com.bankapp.app.repository.BeneficiaryRepository;
@Service
public class BeneficiaryService {
	@Autowired
	private BeneficiaryRepository bene_repo;
	
	@Autowired
	private AccountRepository acc_repo;
	
	public Beneficiary addBeneficiaryList(Beneficiary bene_list)
	{
		return bene_repo.save(bene_list);
	}
	
	public List<Object> getBeneficiaryListByAccountNumber(String myAccountNumber) {
		
		return bene_repo.findByMyAccountNumber(myAccountNumber);
	}
	
	public boolean existsByReceiverAccNo(String receiverAccNo) {
		// TODO Auto-generated method stub
		return bene_repo.existsByReceiverAccNo(receiverAccNo);
	}
	
	public Account getAccount(String accountNumber){
        Account account= acc_repo.findByAccountnumber(accountNumber);
        if(account!=null)
            return account;
        else {
        	return null;
        }
    }
}
