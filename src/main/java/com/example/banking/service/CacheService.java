package com.example.banking.service;

import com.example.banking.dao.CustomerTransactionRepo;
import com.example.banking.dao.EmployeeDetailsRepo;
import com.example.banking.entity.CustomerTransaction;
import com.example.banking.entity.EmployeeDetails;
import com.example.banking.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    public CacheManager cacheManager;

    @Autowired
    private CustomerTransactionRepo customerTransactionRepo;

    @Autowired
    private EmployeeDetailsRepo employeeDetailsRepo;

    public void printCac(String name){
        Cache cache = cacheManager.getCache(name);
        if (cache != null){
            logger.info("Cache " + cache.getNativeCache().toString());
        } else {
            logger.info("Empty " + name);
        }
    }

    public void refreshCacheBalance() {
        logger.info("Refreshing cache customers");
        //cacheManager.getCacheNames().forEach(cache -> logger.info(" All - > " + cacheManager.getCache(cache).getNativeCache().toString()));
        List<CustomerTransaction> list = customerTransactionRepo.findAll();
        if(list.size() > 0 && list != null) {
            cacheManager.getCache(Constants.BALANCE).clear();
            list.forEach(ls -> cacheManager.getCache(Constants.BALANCE).put(ls.getAccountNo(), ls.getBalance()));
            cacheManager.getCache(Constants.CUSTOMER_LIST).clear();
            cacheManager.getCache(Constants.CUSTOMER_LIST).put(SimpleKey.EMPTY, list);
            //cacheManager.getCacheNames().forEach(cache -> logger.info(" After updating cache- > " + cacheManager.getCache(cache).getNativeCache().toString()));
        }
    }

    public void refreshCacheEmployees() {
        logger.info("Refreshing cache employees");
        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepo.findAll();
        if(employeeDetailsList.size() > 0 && employeeDetailsList != null){
            cacheManager.getCache(Constants.EMPLOYEE_LIST).clear();
            cacheManager.getCache(Constants.EMPLOYEE_LIST).put(SimpleKey.EMPTY, employeeDetailsList);
        }
    }



}
