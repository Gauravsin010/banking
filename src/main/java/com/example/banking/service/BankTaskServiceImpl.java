package com.example.banking.service;

import com.example.banking.entity.CustomerTransaction;
import com.example.banking.entity.EmployeeDetails;
import com.example.banking.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BankTaskServiceImpl implements BankTask {

    @Autowired
    private CommonUtil commonUtil;


    private static final Logger logger = LoggerFactory.getLogger(BankTaskServiceImpl.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Async("asyncExecutor")
    @Override
    public CompletableFuture<List<EmployeeDetails>> getEmployeeDetails() {
        logger.info("Inside getEmployeeDetails method");
        try {
            logger.info("Sleeping thread employee");
            logger.info("Start time emp {}", dateFormat.format(new Date()));
            logger.info("Thread name " + Thread.currentThread().getName());
            Thread.sleep(300);
            logger.info("End time is now {}", dateFormat.format(new Date()));
        } catch (Exception ex){
            logger.info("Exception in thread");
        }
        List<EmployeeDetails> employeeDetailsList = commonUtil.getEmployeeDetailsList();
        logger.info("Returning result");
        return CompletableFuture.completedFuture(employeeDetailsList);
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<List<CustomerTransaction>> getCustomerDetails() {
        logger.info("Inside getCustomerDetails method");
        try {
            logger.info("Sleeping thread customer");
            logger.info("Start time cus {}", dateFormat.format(new Date()));
            logger.info("Thread name " + Thread.currentThread().getName());
            Thread.sleep(300);
            logger.info("End time is now {}", dateFormat.format(new Date()));
        } catch (Exception ex){
            logger.info("Exception in thread");
        }
        List<CustomerTransaction> customerTransactions = commonUtil.getCustomerList();
        logger.info("Returning result");
        return CompletableFuture.completedFuture(customerTransactions);
    }
}
