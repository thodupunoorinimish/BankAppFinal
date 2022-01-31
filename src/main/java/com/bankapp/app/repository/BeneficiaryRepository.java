package com.bankapp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.app.domain.Beneficiary;


@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
	
	List<Object> findByMyAccountNumber(String myAccountNumber);

	



	

}
