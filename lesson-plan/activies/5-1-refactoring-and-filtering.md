# Refactoring and Filtering 
**Time Estimate: 20 Minutes**

```java
@GetMapping("/transactions/filter")
List<Transaction> findCharges(@RequestParam double value) {
    return repository.findWithValueGreaterThan(value);
}
```

# Student Tasks
* Refactor your old delete method to soft-delete a `Transaction` instead by updating its softDelete field.
* Create a new `/transactions/sum` end point that provides a sum of all *active* charges.
* Create a new custom query and corresponding endpoint for finding transaction greater than a value: 
  * End point `/transactions/filter`
  * Use either a `@PathVariable` to retrieve a transactionValue to filter by.
    * **OR** Experiment with using `@RequestParam` which allowes for urls such as: 
      `http://localhost:8080/transactions/filter?value=200` instead of `http://localhost:8080/transactions/filter/200`
  * Using the signature below write a `@Query` that finds all active transactions with a value greater than the binding variable`:transavtionValue`. 
    `@Param` takes a string as input that matches a `:variable` and binds it to the signature variable.
```java
List<Transaction> findWithValueGreaterThan(@Param("transactionValue") double transactionValue);
```

