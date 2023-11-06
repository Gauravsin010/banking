package com.example.banking.util;

import com.example.banking.dao.CustomerTransactionRepo;
import com.example.banking.entity.CustomerTransaction;
import com.example.banking.exception.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommonUtil {

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;

    public static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public Integer getBalanceFromAccountNo (Integer accountNo) {
        try {
            logger.info("Inside getBalanceFromAccountNo method");
            if(accountNo > 0) {
                List<CustomerTransaction> customerTransaction = customerTransactionRepo.customerDetailsFromAccountNo(accountNo);
                Integer balance = customerTransaction.get(0).getBalance();
                return balance;
            } else {
                logger.info("Invalid Account No.");
            }

        } catch (Exception ex){
            logger.info("Exception in getBalanceFromAccountNo Method");
            throw new TransactionException("Exception in getBalanceFromAccountNo", ex);
        }
        return -1;
    }

}
