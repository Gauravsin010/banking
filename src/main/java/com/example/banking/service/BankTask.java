package com.example.banking.service;

import com.example.banking.entity.CustomerTransaction;
import com.example.banking.entity.EmployeeDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BankTask {

    public CompletableFuture<List<EmployeeDetails>> getEmployeeDetails();

    public CompletableFuture<List<CustomerTransaction>> getCustomerDetails();

    public CompletableFuture<List<EmployeeDetails>> getEmployeeDetailsDb();

    public CompletableFuture<List<CustomerTransaction>> getCustomerDetailsDb();

    public ResponseEntity<String> createAccount(CustomerTransaction customerTransaction);

}
