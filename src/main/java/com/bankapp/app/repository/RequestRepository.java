package com.bankapp.app.repository;

import com.bankapp.app.domain.Requests;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Requests, Integer> {

    @Query(value = "select * from requests where request_id = ?1", nativeQuery = true)
    Requests getRequestDetails(int requestId);

    @Query(value = "select * from requests where to_account = ?1 and status = 'PENDING'", nativeQuery = true)
    List<Requests> getAllPendingRequests(String accountNumber);

    @Query(value = "select * from requests where to_account = ?1 and status = 'COMPLETED'", nativeQuery = true)
    List<Requests> getAllCompletedRequests(String accountNumber);

    @Query(value = "select * from requests where to_account = ?1 and status = 'DECLINED'", nativeQuery = true)
    List<Requests> getAllDeclinedRequests(String accountNumber);

    @Query(value = "select * from requests where from_account = ?1", nativeQuery = true)
    List<Requests> getAllUserRequests(String accountNumber);

    @Query(value = "delete from requests where from_account = ?1 and request_id = ?2 and status = false", nativeQuery = true)
    Requests deleteUserRequest(String accountNumber, int requestId);

}