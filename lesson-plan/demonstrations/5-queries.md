# Custom Queries with Spring Data
**Time Estimate: 10 Minutes**

At this point the students should have a fully functional CRUD REST API that performs the very basic
operations a developer would expect. Below is an example of what their class could look like up to this point:
```java
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
        return repository.findAllActiveTransactions();
    }

    @PostMapping("/transactions")
    Transaction createTransaction(@RequestBody Transaction transaction) {
        return repository.save(transaction);
    }

    @GetMapping("/transactions/{id}")
    Transaction getTransaction(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> transactionNotFound(id));
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
    void deleteTransaction(@PathVariable Long id) {
        repository.findById(id)
                .map(transaction -> {
                    transaction.setSoftDelete(true);
                    return repository.save(transaction);
                })
                .orElseThrow(() -> transactionNotFound(id));
    }
}
```

The last thing to showcase is how a student could write a custom query with Spring Data.
To do this add the following code to your `TransactionRepository` interface. This query will find all
transactions that have not been soft-deleted. Soft-deleting is a way to keep a history of records without
actually deleting them.

```java
@Query("SELECT t FROM Transaction t WHERE t.softDelete = false")
List<Transaction> findAllActiveTransactions();
```

Now let's move onto the final tasks and wrap up the class:  
[5.1. Refactoring and Filtering](../activies/5-1-refactoring-and-filtering.md)
