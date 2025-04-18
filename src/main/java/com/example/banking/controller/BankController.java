package com.example.banking.controller;

import com.example.banking.dao.CustomerTransactionRepo;
import com.example.banking.entity.CustomerTransaction;
import com.example.banking.entity.EmployeeDetails;
import com.example.banking.exception.TransactionException;
import com.example.banking.model.Bank.BankDetails;
import com.example.banking.service.BankTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;

    @Autowired
    private BankTask bankTask;

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    @GetMapping("/allAccountInfo")
    public List<CustomerTransaction> getAllCustomerInfo(){
        try {
            logger.info("Inside allAccountInfo controller");
            List<CustomerTransaction> customerTransactions = customerTransactionRepo.findAll();
            return customerTransactions;
        } catch (Exception ex){
            logger.info("Exception inside allAccountInfo controller");
            throw new TransactionException("Exception inside allAccountInfo", ex);
        }
    }

    @GetMapping("/getAll")
    public BankDetails getAll(){
        try {
            logger.info("Inside allAccountInfo controller");
            CompletableFuture<List<EmployeeDetails>> employeeDetails = bankTask.getEmployeeDetails();
            CompletableFuture<List<CustomerTransaction>> customerTransaction = bankTask.getCustomerDetails();
            BankDetails bankDetails = new BankDetails();
            bankDetails.setEmployeeDetailsList(employeeDetails.get());
            bankDetails.setCustomerTransactionList(customerTransaction.get());

//            CompletableFuture<String> future1
//                    = CompletableFuture.supplyAsync(() -> "Hello");
//            CompletableFuture<String> future2
//                    = CompletableFuture.supplyAsync(() -> "Beautiful");
//            CompletableFuture<String> future3
//                    = CompletableFuture.supplyAsync(() -> "World");
//
//            CompletableFuture<Void> combinedFuture
//                    = CompletableFuture.allOf(future1, future2, future3);
//
//// ...
//
//            combinedFuture.get();

            logger.info("Returning result");
            return bankDetails;

        } catch (Exception ex){
            logger.info("Exception inside getAll controller");
            throw new TransactionException("Exception inside getAll", ex);
        }
    }
}
