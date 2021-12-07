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

    @Bean
    CommandLineRunner initDatabase(TransactionRepository repository) {
        if (repository.findAll().size() < 1) {
            return args -> {
                log.info("Createing new database.");
                log.info("Creating Sample transaction: " + repository.save(new Transaction("Clark Kent", "Barry Allen", 50.00)));
                log.info("Creating Sample transaction: " + repository.save(new Transaction("Diana Prince", "Kendra Saunders", 125.50)));
                log.info("Creating Sample transaction: " + repository.save(new Transaction("Tony Stark", "Peter Parker", 250.00)));
                log.info("Creating Sample transaction: " + repository.save(new Transaction("Natalia Romanoff", "Carol Danvers", 37.75)));
            };
        } else {
            return args -> {
                log.info("Loading database from file.");
            };
        }
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "8081");
    }
}
