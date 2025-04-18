package com.example.banking.scheduler;

import com.example.banking.dao.CustomerTransactionRepo;
import com.example.banking.entity.CustomerTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CustomerScheduler {

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;

    private static final Logger logger = LoggerFactory.getLogger(CustomerScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    public void getBalance(){
        logger.info("Get Balance Method");
        logger.info("The time is now {}", dateFormat.format(new Date()));
        List<CustomerTransaction> list = customerTransactionRepo.findAll();
        list.forEach(ls -> logger.info(ls.getAccountNo() + "  " + ls.getBalance()));
    }
}
