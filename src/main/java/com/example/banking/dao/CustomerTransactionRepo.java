package com.example.banking.dao;

import com.example.banking.entity.CustomerTransaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public interface CustomerTransactionRepo extends JpaRepository<CustomerTransaction,Integer> {//CrudRepository<CustomerTransaction,Integer> {

    @Override
    List<CustomerTransaction> findAll();

    @Query(value = "SELECT * FROM customer WHERE AccountNo= :accountNo",nativeQuery = true)
    List<CustomerTransaction> customerDetailsFromAccountNo(@Param("accountNo") Integer accountNo);

    List<CustomerTransaction> findByAccountNo(Integer AccountNo);

    @Modifying
    @Query(value = "START TRANSACTION", nativeQuery = true)
    void startTransaction();

    @Modifying
    @Query(value = "ROLLBACK", nativeQuery = true)
    void rollback();

    @Modifying
    @Query(value = "COMMIT", nativeQuery = true)
    void commit();

    @Modifying
    @Query(value = "UPDATE customer SET Balance= :balance WHERE AccountNo= :accountNo", nativeQuery = true)
    void updateBalance(@Param("accountNo") Integer accountNo, @Param("balance") Integer balance);

    @Modifying
    @Query(value = "INSERT INTO customer VALUES(:AccountNo, :FirstName, :LastName, :Address, :Balance, :AccountType)", nativeQuery = true)
    void createNewAccount(@Param("AccountNo") Integer AccountNo, @Param("FirstName") String FirstName, @Param("LastName") String LastName,
                          @Param("Address") String Address, @Param("Balance") Integer Balance, @Param("AccountType") String AccountType);

}


