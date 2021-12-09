# Exposing data as a REST API
**Time Estimate: 10 Minutes**

Now that students have a basic understanding of how data is being stored, it's time to expose it. 
To do this, explain that we will be creating a **REST** API. **REST** is not a standard, but is a collection of 
constraints for how an HTTP based API is modeled. Explain that in web development it is commonly referred 
to as a stateless API following a client-server model where the client provides all required information each
request. In the scope of web apps the industry has settled on returning **JSON** as the response to client
requests.  

To being creating our API, create a `Controllers` package and a `TransactionController` class annotated with `@RestController`.
Then add a private final `TransactionRepository` and a constructor that accepts a `TransactionRepository`
and instantiates the class field.

```java
@RestController
public class TransactionController {

    private final TransactionRepository repository;
    
    TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }
}
```

Next add a request mapping that will route to `/transactions`:
```java
@GetMapping("/transactions")
List<Transaction> all() {
    return repository.findAll();
}
```

Next have students restart their apps with the above and navigate to `http://localhost:8080/transactions`.
They should be greeted with the JSON matching what they found in their database from the previous activity.

We may not always want to display all of the results so show the students how to use Path Parameters 
with the following:
```java
@GetMapping("/transactions/{id}")
Transaction one(@PathVariable Long id) {
    return repository.findById(id)
    .orElseThrow(() -> new EntityNotFoundException("Transaction: " + id + " not found."));
}
```
Have students test out the new end point with different ids that exist and some that do not.  
Now move onto the next activity where students will be given the other common REST mappings to fill out their
API.  
[4.1 Filling out the REST of the API](../activies/4-1-rest.md)