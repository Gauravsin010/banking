package com.example.banking.scheduler;

import com.example.banking.dao.CustomerTransactionRepo;
import com.example.banking.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CustomerScheduler {

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;

    @Autowired
    private CacheService cacheService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 300000, initialDelay = 15000)
    public void refreshCustomers(){
        logger.info("Refreshing customers cache method");
        logger.info("The time is now {}", dateFormat.format(new Date()));
        cacheService.refreshCacheBalance();
    }

    @Scheduled(fixedRate = 600000, initialDelay = 10000)
    public void refreshEmployees(){
        logger.info("Refreshing employees cache method");
        logger.info("The time is now {}", dateFormat.format(new Date()));
        cacheService.refreshCacheEmployees();
    }

}
