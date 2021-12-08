package com.tutorial.Ledger.Configuration;

import com.tutorial.Ledger.Entities.Transaction;
import com.tutorial.Ledger.Repositories.TransactionRepository;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

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

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8081");
    }
}
