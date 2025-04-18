package com.example.banking.service;

import com.example.banking.dao.CustomerTransactionRepo;
import com.example.banking.entity.CustomerTransaction;
import com.example.banking.exception.TransactionException;
import com.example.banking.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class TransactionTaskServiceImpl implements TransactionTask{

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;

    @Autowired
    private CommonUtil commonUtil;

    private static final Logger logger = LoggerFactory.getLogger(TransactionTaskServiceImpl.class);


    @Override
    @Cacheable(value = "balance", key = "#accountNo")
    public Integer getBankBalance(Integer accountNo) {
        try {
            logger.info("Inside getBankBalance method");
            Integer balance = commonUtil.getBalanceFromAccountNo(accountNo);
            if(balance >= 0){
                return balance;
            } else {
                return -1;
            }

        } catch (Exception ex){
            logger.info("Exception in getBankBalance Method");
            throw new TransactionException("Exception in getBankBalance", ex);
        }
    }

    @Override
    public String transferMoney(int accountNo, int senderAccountNo, int amount) {
        try {
            logger.info("Inside transferMoney method");
            if(accountNo > 0 && senderAccountNo > 0 && amount > 0) {
                int balance = commonUtil.getBalanceFromAccountNo(accountNo);
                int receiverBalance = commonUtil.getBalanceFromAccountNo(senderAccountNo);
                if (balance > 0) {
                    if (balance > amount){
                        int updatedBalance = balance - amount;
                        int receiverUpdatedBalance = receiverBalance + amount;
                        try {
                            customerTransactionRepo.startTransaction();
                            customerTransactionRepo.updateBalance(accountNo, updatedBalance);
                            customerTransactionRepo.updateBalance(senderAccountNo, receiverUpdatedBalance);
                            customerTransactionRepo.commit();
                            logger.info("Transaction Successfull");
                            return "Transaction Successfull";
                        } catch (Exception ex){
                            customerTransactionRepo.rollback();
                            logger.info("Exception in transferring money");
                        }
                    } else {
                        logger.info("Insufficient balance " + balance);
                    }
                } else {
                    logger.info("Invalid accountNo");
                }
            }
        } catch (Exception ex){
            logger.info("Exception in transferMoney method");
            throw new TransactionException("Exception in transferMoney method", ex);
        }
        return "Transaction Unsuccessfull";
    }

    @Override
    public String createAccount(CustomerTransaction customerTransaction) {
        try {
            logger.info("Inside createAccount method");
            customerTransactionRepo.createNewAccount(customerTransaction.getAccountNo(), customerTransaction.getFirstName(),
                    customerTransaction.getLastName(), customerTransaction.getAddress(), customerTransaction.getBalance(),
                    customerTransaction.getAccountType());
            return "Ok";
        } catch (Exception ex){
            logger.info("Exception in createAccount Method");
            throw new TransactionException("Exception in createAccount", ex);
        }
    }
}
