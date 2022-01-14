package com.bankapp.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.bankapp.app.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	boolean existsByAccountnumber(String accountnumber);

	User findByAccountnumber(String accountnumber);

	boolean existsByUsername(String name);

	User findByUsername(String userName);

}
