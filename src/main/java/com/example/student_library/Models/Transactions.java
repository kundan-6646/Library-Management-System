package com.example.student_library.Models;

import com.example.student_library.Enums.TransactionStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String uuid = UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date transactionDate;

    private boolean isIssue;

    @ManyToOne
    @JoinColumn
    private Card card;

    @ManyToOne
    @JoinColumn
    private Book book;

    public Transactions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public boolean isIssue() {
        return isIssue;
    }

    public void setIssue(boolean issue) {
        isIssue = issue;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
