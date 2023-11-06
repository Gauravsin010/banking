package com.example.banking.controller;

import com.example.banking.dao.CustomerTransactionRepo;
import com.example.banking.entity.CustomerTransaction;
import com.example.banking.exception.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;

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
}
