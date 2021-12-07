package com.tutorial.Ledger.Repositories;

import com.tutorial.Ledger.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.softDelete = false")
    List<Transaction> findAllActiveTransactions();
}

