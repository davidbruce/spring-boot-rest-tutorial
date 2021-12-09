# Filling out the REST of the API. 
**Time Estimate: 20 Minutes**

So far students have only addressed the R in **CRUD** and now it's time for them to implement the REST. 
This section will utilize cURL to perform different HTTP requests. cURL is available by default on most
unix systems, but students should have had it installed before starting. For Windows users it is encouraged
to use the cURL that comes with Git Bash.  More advanced Windows users can use whichever method they are comfortable
with obtaining cURL.

# Student Tasks
Perform the following to have a complete CRUD via a REST API:

* Use the signature below and attempt to fill out a method that saves a new transaction to the database. Then
  use the cURL command below to create a new transaction.
```java  
@PostMapping("/transactions")
Transaction createTransaction(@RequestBody Transaction transaction) {}
```
```
curl -X POST http://localhost:8080/transactions -H 'Content-type:application/json' -d '{"sender": "Bruce Wayne", "recipient": "Tony Stark", "transactionValue": 50000.00}'
```
* Use the signature below and attempt to fill out a method that updates *just* the transactionValue of a 
  single transaction. Then run the cURL command to change the value of a transaction.
```java 
@PutMapping("/transactions/{id}")
Transaction updateTransaction(@RequestBody double newValue, @PathVariable Long id) {}
```

```
curl -X PUT http://localhost:8080/transactions/IDHERE -H 'Content-type:application/json' -d '50.0'
```

* Use the signature below and attempt to fill out a method that deletes a transaction by id. Then run the
  cURL command to remove a transaction.
```java  
@DeleteMapping("/transactions/{id}")
void deleteTransaction(@PathVariable Long id) 
```
```
curl -X DELETE http://localhost:8080/transactions/IDHERE 
```

Next up is a demonstration on how to write custom SQL queries with Spring Boot.  
[5. Custom Queries with Spring Data](../demonstrations/5-queries.md)  