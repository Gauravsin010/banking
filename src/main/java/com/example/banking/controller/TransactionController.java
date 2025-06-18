package com.example.banking.controller;

import com.example.banking.dao.CustomerTransactionRepo;
import com.example.banking.entity.CustomerTransaction;
import com.example.banking.exception.TransactionException;
import com.example.banking.service.CacheService;
import com.example.banking.service.TransactionTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionTask transactionTask;

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;

    @Autowired
    private CacheService cacheService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @GetMapping("/getBalance/AccountNo")
    public int getBankBalance(@RequestHeader int accountNo){
        try {
            logger.info("Inside getBankBalance controller");
            int balance =  transactionTask.getBankBalance(accountNo);
            cacheService.printCac("balance");
            return balance;
        } catch (Exception ex){
            logger.info("Exception in getBankBalance Method");
            throw new TransactionException("Exception in getBankBalance Method", ex);
        }
    }

    @PostMapping("/transfer")
    public String transferMoney(@RequestHeader int accountNo,@RequestHeader int senderAccountNo, @RequestHeader int amount){
        try {
            logger.info("Inside transferMoney controller");
            String result = transactionTask.transferMoney(accountNo, senderAccountNo, amount);
            return result;
        } catch (Exception ex){
            logger.info("Exception inside transferMoney controller");
            throw new TransactionException("Exception inside transferMoney controller", ex);
        }
    }

    @PutMapping("/create/newAccount")
    public String createAccount(@RequestBody CustomerTransaction customerTransaction){
        try {
            logger.info("Inside createAccount controller");
            String result = transactionTask.createAccount(customerTransaction);
            return result;
        } catch (Exception ex){
            logger.info("Exception inside createAccount controller");
            throw new TransactionException("Exception inside createAccount controller", ex);
        }
    }

    @GetMapping("/account/{AccountNo}")
    public ResponseEntity<CustomerTransaction> getAccountDetails(@PathVariable Integer AccountNo){
        try {
            logger.info("Inside method");
            logger.info("Account No " + AccountNo);
            CustomerTransaction customerTransaction = customerTransactionRepo.findByAccountNo(AccountNo).get(0);

            return new ResponseEntity<>(customerTransaction, HttpStatus.ACCEPTED);
        } catch (Exception ex){
            logger.info("Exception inside getAccountDetails controller");
            throw new TransactionException("Exception inside getAccountDetails controller", ex);
        }
    }

}
