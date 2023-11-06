package com.example.banking.exception;

public class TransactionException extends RuntimeException{
    public TransactionException(String message, Throwable ex){
        super(message,ex);
    }
}
