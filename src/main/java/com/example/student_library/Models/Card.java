package com.example.student_library.Models;

import com.example.student_library.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedDate;

    @Enumerated(value = EnumType.STRING)
    private CardStatus status;

    private int fine;

    @OneToOne
    @JoinColumn
    private Student student; //this var is used in parent class while doing bidirectional mapping

    //Card is parent wrt to Book
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    List<Book> booksIssued = new ArrayList<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    List<Transactions> transactions = new ArrayList<>();

    public Card() {
    }

    public Card(int id, Date createdOn, Date updatedDate, CardStatus status) {
        this.id = id;
        this.createdOn = createdOn;
        this.updatedDate = updatedDate;
        this.status = status;
    }

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
