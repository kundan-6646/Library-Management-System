package com.example.student_library.DTOs;

public class AuthorEntryDTO {
    //This is just an object that will be used to take request
    //from PostMan


    //It will contain the parameters that we want to send from postman

    //id is not here because we don't want to send it from Postman

    private String name;

    private int age;

    private double rating;

    public String getName() {
        return name;
    }

    public AuthorEntryDTO(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
