package com.example.banking.service;

import com.example.banking.dao.CustomerTransactionRepo;
import com.example.banking.dao.EmployeeDetailsRepo;
import com.example.banking.entity.CustomerTransaction;
import com.example.banking.entity.EmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BankTaskServiceImpl implements BankTask{

    @Autowired
    private EmployeeDetailsRepo employeeDetailsRepo;

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;


    private static final Logger logger = LoggerFactory.getLogger(BankTaskServiceImpl.class);


    @Async
    @Override
    public CompletableFuture<List<EmployeeDetails>> getEmployeeDetails() {
        logger.info("Inside getEmployeeDetails method");
        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepo.findAll();
        logger.info("Returning result");
        return CompletableFuture.completedFuture(employeeDetailsList);
    }

    @Async
    @Override
    public CompletableFuture<List<CustomerTransaction>> getCustomerDetails() {
        logger.info("Inside getCustomerDetails method");
        List<CustomerTransaction> customerTransactions = customerTransactionRepo.findAll();
        logger.info("Returning result");
        return CompletableFuture.completedFuture(customerTransactions);
    }
}
