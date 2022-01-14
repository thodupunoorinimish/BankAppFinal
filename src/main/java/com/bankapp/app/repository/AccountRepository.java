package com.bankapp.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.bankapp.app.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

	boolean existsByAccountnumber(String accountnumber);

	Account findByAccountnumber(String accountnumber);

	

	boolean existsByPhonenumber(String phonenumber);

	Account findByPhonenumber(String phonenumber);

	

}
