package com.example.banking.model.Bank;

import com.example.banking.entity.CustomerTransaction;
import com.example.banking.entity.EmployeeDetails;

import java.util.List;

public class BankDetails {

    private List<CustomerTransaction> customerTransactionList;

    private List<EmployeeDetails> employeeDetailsList;

    public List<CustomerTransaction> getCustomerTransactionList() {
        return customerTransactionList;
    }

    public void setCustomerTransactionList(List<CustomerTransaction> customerTransactionList) {
        this.customerTransactionList = customerTransactionList;
    }

    public List<EmployeeDetails> getEmployeeDetailsList() {
        return employeeDetailsList;
    }

    public void setEmployeeDetailsList(List<EmployeeDetails> employeeDetailsList) {
        this.employeeDetailsList = employeeDetailsList;
    }

    @Override
    public String toString() {
        return "BankDetails{" +
                "customerTransactionList=" + customerTransactionList +
                ", employeeDetailsList=" + employeeDetailsList +
                '}';
    }

}
