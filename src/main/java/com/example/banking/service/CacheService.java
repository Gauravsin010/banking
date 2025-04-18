package com.example.banking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    public CacheManager cacheManager;

    public void printCac(String name){
        Cache cache = cacheManager.getCache(name);
        if (cache != null){
            logger.info("Cache " + cache.getNativeCache().toString());
        } else {
            logger.info("Empty " + name);

        }
    }
}
