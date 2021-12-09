package com.tutorial.Ledger.Repositories;

import com.tutorial.Ledger.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.softDelete = false")
    List<Transaction> findAllActiveTransactions();

    @Query("SELECT t FROM Transaction t WHERE t.softDelete = false AND t.transactionValue > :transactionValue")
    List<Transaction> findWithValueGreaterThan(@Param("transactionValue") double transactionValue);
}

