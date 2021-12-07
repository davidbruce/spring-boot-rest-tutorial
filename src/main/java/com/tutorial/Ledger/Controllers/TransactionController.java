package com.tutorial.Ledger.Controllers;

import com.tutorial.Ledger.Entities.Transaction;
import com.tutorial.Ledger.Repositories.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class TransactionController {
    private final TransactionRepository repository;

    private EntityNotFoundException transactionNotFound(Long id) {
        return new EntityNotFoundException("Transaction: " + id + " not found.");
    }

    TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/transactions")
    List<Transaction> all() {
        return repository.findAll();
    }

    @GetMapping("/transactions/{id}")
    Transaction one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> transactionNotFound(id));
    }

    @GetMapping("/transactions/sum")
    double sumActive() {
        return repository.findAllActiveTransactions().stream()
                .map(Transaction::getTransactionValue)
                .reduce(0.0, Double::sum);
    }

    @PutMapping("/transactions/{id}")
    Transaction updateTransaction(@RequestBody double newValue, @PathVariable Long id) {
        return repository.findById(id)
                .map(transaction -> {
                    transaction.setTransactionValue(newValue);
                    return repository.save(transaction);
                })
                .orElseThrow(() -> transactionNotFound(id));

    }

    @DeleteMapping("/transactions/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.findById(id)
                .map(transaction -> {
                    transaction.setSoftDelete(true);
                    return repository.save(transaction);
                })
                .orElseThrow(() -> transactionNotFound(id));
    }
}
