# Verifying the Data Saved
**Time Estimate: 15 Minutes**

In this activity, we will explore the `transaction` table automatically created by Spring Data.
First, have the students add the following `@Bean` to their `LoadDatabase` class. This will open up the
embedded H2 database to direct localhost access on port 8081.

```java
@Bean(initMethod = "start", destroyMethod = "stop")
public Server h2Server() throws SQLException {
    return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8081");
}
```
Have the students re-run their app with the above added. Next have the students open a SQL Development Tool
with JDBC support such as DBeaver, DataGrip, etc. Tools like DataGrip and DBeaver will automatically
download the necessary drivers when connecting to a database.

When connecting the credentials should match the `application.properties`.
* host: localhost
* port: 8081
* database/scheme: ./data/ledger
* username: sa
* password: empty

Below is an example of the connection dialog for the H2 Server Driver in DBeaver:

![DBeaver Connection Prompt](../images/Database%20Connection.png)

# Student Tasks
Perform the following tasks in the `Public` schema:

* Run a select query against the `Transactions` table and verify the default transactions were saved.
* Insert a new transaction with SQL and restart the application.  Note the number of transactions logged.
* Delete all of the transactions with SQL and restart the applications.  Note that the default transactions
  are recreated.

After the tasks have been completed move onto:  
[4. Exposing data as a REST API](4-controllers.md) 
