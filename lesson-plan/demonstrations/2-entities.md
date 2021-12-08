# Connecting to the Database and Entities
**Time Estimate: 20 Minutes**

Now that the students have the application running it's time to give it some functionality.
To make the process as painless as possible the app was generated with the **H2** database as a
dependency. This is a pure Java, embedded, database that makes it trivial bootstrap a project with
a relational database. Generally H2 is utilized as an in-memory database, but for this project we 
will be using its file mode. This saves the entire database to a file so that it can persist data.

To start using H2 with Spring Boot open the  `application.properties` found in 
the `resources` package.

Below is the application configuration you should give your students: 
```properties
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:file:${user.dir}/data/ledger
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

Once that is setup we can start creating entities in the application that will automatically 
create tables in the database. 

To start have the students create an `Entities` package under `com.example.demo` or whatever their
main package is named. This can be done by right-clicking the main package `New --> Package` and 
then typing Entities into the prompt. 

Then similarly right-click the new `Entities` package `New --> Java Class` and enter `Transaction`
into the prompt.  Next fill out the newly created Java class with the following:

```java
import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String sender;
    @Column(nullable = false)
    private String recipient;
    @Column(nullable = false)
    private double transactionValue;
    private boolean softDelete = false;

    public Transaction() {}

}
```

Explain that first we need to annotate the class with `@Entity` which this indicates to the Java 
Persistence API (JPA) that the class can be mapped to a table. Next we annotate the class with
`@Table` to indicate what the table in the database will be called.  

Next fill out the class with the properties shown and explain the following annotations:
* `@Id` indicates to JPA that this column is mapped to a primary key.
* `@GeneratedValue` the value for this is being automatically generated by the database.
   The `GenerationType.AUTO` allows the driver to determine the best strategy for how the
   primary key will be created.
* `@Column` allows developers to change the way JPA maps a property to a column. In this case we 
   are stating that this column cannot be empty.

Next right-click below the empty constructor and select `Generate` from the context menu. Then
generate a constructor selecting `sender`, `recipient`, and `transactionValue`.  This a great way
to showcase how useful IntelliJ can be for quickly creating boilerplate. 

![Prompt for Constructor Generation](../images/Generate%20Constructor.png)

Then generate the following similar to the constructor:
* `Getter` for `id`
* `Getter and Setter` for `sender`, `sender`, `recipient`, `transactionValue`, and `softDelete`
* `equals() and hashCode()` follow the prompts keeping the defaults, but select *both non-nullable* fields 
  in the final prompt
* `toString()` selecting all values.

Next up we will go over repositories and saving data into the database.  
[3. Repositories](3-repositories.md)