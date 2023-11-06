package com.example.banking.service;


import com.example.banking.entity.CustomerTransaction;

public interface TransactionTask {

    public Integer getBankBalance(Integer accountNo);

    public String transferMoney(int accountNo, int senderAccountNo, int amount);

    public String createAccount(CustomerTransaction customerTransaction);

}
