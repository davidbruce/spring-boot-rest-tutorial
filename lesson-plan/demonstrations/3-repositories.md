# Querying the Database with Repositories
**Time Estimate: 20 Minutes**

Now that we have an Entity it's time to show the students how they can easily access the underlying 
table with Spring Data.  

First, create a new `Repositories` package under the main package, and create a `TransactionRepository` interface
under it. Next simply extend the new interface with `JpaRepository<Transaction, Long>`. `JpaRepository<T, ID>` 
has two type parameters the first is the entity you would like to query against.  The second parameter is
the type of the id column for the entity.

The interface should look like the following:
```java
public interface TransactionRepository extends JpaRepository<Transaction, Long> { }
```

Explain that the above is all you need for basic **CRUD**` operations on the `Transaction` table.
**CRUD** which stands for Create, Read, Update, Delete and Sprin Data repositories automatically gives
you methods for each.


Now let's load some default entities into the database. Create a new `Configuration` package under the main package.
Then create a new java class named `LoadDatabase` under `Configuration`. Annotate this new class with 

`@Configuration` as such:
```java
@Configuration
class LoadDatabase {
}
```

Explain that the `@Configuration` annotation tells Spring that this class contains methods annotated with `@Bean` and 
run once during application startup. `@Bean` methods that return a `CommandLineRunner` are run after 
everything else has finished loading.

Next create a static final logger called log for the purposes of printing to the console.
```java
private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
```
Then create a helper function for creating some default `transactions`. Here you can explain
that by calling `repository.save()` will generate an `INSERT` or `UPDATE` SQL statement with the corresponding
values to the object passed to it.
```java
private void createTransaction(TransactionRepository repository, String sender, String recipient, double transactionValue) {
    log.info("Creating Sample transaction: " + repository.save(new Transaction(sender, recipient, transactionValue)));
}
```
Next a function that calls the above with four `transactions`:
```java
private void createDefaultTransactions(TransactionRepository repository) {
    createTransaction(repository, "Clark Kent", "Barry Allen", 50.00);
    createTransaction(repository, "Diana Prince", "Kendra Saunders", 125.50);
    createTransaction(repository, "Tony Stark", "Peter Parker", 250.00);
    createTransaction(repository, "Natalia Romanoff", "Carol Danvers", 37.75);
}
```

Now finally for the `@Bean` that will populate the database. Take note that this is a `CommandLineRunner`
method, so it will run after Spring has already created its connection to the database. The below function
will first check if there are any `transactions` in the database, if none are found it will call our
`createDefaultTransactions` function. The `repository.findAll()` automatically generates a `SELECT *` SQL statement
for the transactions table.  This represents the read porition of **CRUD**. 

```java
@Bean
CommandLineRunner initDatabase(TransactionRepository repository) {
    int transactionCount = repository.findAll().size();
    log.info("Checking database for transactions.");
    if (transactionCount == 0) {
        return args -> {
            log.info("Loaded database is empty, creating default transactions.");
            defaultTransactions(repository);
        };
    } else {
        return args -> {
            log.info("The loaded database has " + transactionCount + " transactions.");
        };
    }
}
```


The complete class should look like the following:
```java 
@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    private void createTransaction(TransactionRepository repository, String sender, String recipient, double transactionValue) {
        log.info("Creating Sample transaction: " + repository.save(new Transaction(sender, recipient, transactionValue)));
    }

    private void createDefaultTransactions(TransactionRepository repository) {
        createTransaction(repository, "Clark Kent", "Barry Allen", 50.00);
        createTransaction(repository, "Diana Prince", "Kendra Saunders", 125.50);
        createTransaction(repository, "Tony Stark", "Peter Parker", 250.00);
        createTransaction(repository, "Natalia Romanoff", "Carol Danvers", 37.75);
    }

    @Bean
    CommandLineRunner initDatabase(TransactionRepository repository) {
        int transactionCount = repository.findAll().size();
        log.info("Checking database for transactions.");
        if (transactionCount == 0) {
            return args -> {
                log.info("Loaded database is empty, creating default transactions.");
                createDefaultTransactions(repository);
            };
        } else {
            return args -> {
                log.info("The loaded database has " + transactionCount + " transactions.");
            };
        }
    } 
}
```

Now when the app is started you will see a log showing that it has either found or created some default
transactions.  

Next work with the students on the following activity to verify that the default entities were properly
inserted into the database.  
[3.1 Verify the Data](../activies/3-1-sql.md)