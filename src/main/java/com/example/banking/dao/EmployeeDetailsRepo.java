package com.example.banking.dao;

import com.example.banking.entity.EmployeeDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetails, Integer> {

    @Override
    public List<EmployeeDetails> findAll();


}
