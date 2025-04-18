package com.example.banking.service;

import com.example.banking.entity.CustomerTransaction;
import com.example.banking.entity.EmployeeDetails;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BankTask {

    public CompletableFuture<List<EmployeeDetails>> getEmployeeDetails() throws InterruptedException;

    public CompletableFuture<List<CustomerTransaction>> getCustomerDetails();

}
