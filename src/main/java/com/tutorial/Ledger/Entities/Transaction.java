package com.tutorial.Ledger.Entities;

import javax.persistence.*;
import java.util.Objects;

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

    public Transaction(String sender, String recipient, double transactionValue) {
        this.sender = sender;
        this.recipient = recipient;
        this.transactionValue = transactionValue;
    }

    public long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transaction = (Transaction) o;
        return id == transaction.id && softDelete == transaction.softDelete && Double.compare(transaction.transactionValue, transactionValue) == 0 && sender.equals(transaction.sender) && recipient.equals(transaction.recipient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, recipient, softDelete, transactionValue);
    }

    @Override
    public String toString() {
        return "Ledger{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", softDelete=" + softDelete +
                ", transactionValue=" + transactionValue +
                '}';
    }
}