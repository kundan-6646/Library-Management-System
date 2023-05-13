package com.example.student_library.Models;

import com.example.student_library.Enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int pages;
    private boolean issued;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    //Book is child wrt to author
    //Setting here the foreign key : Standard 3 steps

    @ManyToOne
    @JoinColumn //Add an extra attribute of authorId (parent table PK) for the foreign key of child table
    private Author author; //This is the parent entity we are connecting with

    //Book is also child wrt Card...
    @ManyToOne
    @JoinColumn
    private Card card;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<Transactions> transactions = new ArrayList<>();

    public Book() {
        this.issued = false;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
