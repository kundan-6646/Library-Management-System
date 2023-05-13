package com.example.student_library.Services;

import com.example.student_library.DTOs.BookRequestDto;
import com.example.student_library.Models.Author;
import com.example.student_library.Models.Book;
import com.example.student_library.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookRequestDto bookRequestDto) {
        //I want to get the AuthorEntity
        int authorId = bookRequestDto.getAuthorId();

        Author author = authorRepository.findById(authorId).get();

        Book book = new Book();
        book.setName(bookRequestDto.getName());
        book.setGenre(bookRequestDto.getGenre());
        book.setPages(bookRequestDto.getPages());
        book.setIssued(false);

        //Setting the foreign key attr in the child class :
        book.setAuthor(author);

        List<Book> booksWrittenByAuthor = author.getBooksWritten();
        booksWrittenByAuthor.add(book);

        //Now the book is to be saved, but also author is also to be saved.

        //Why do we need to again save (updating) the author ?? bcz
        //bcz the author Entity has been updated....we need to re-save/update it.

        authorRepository.save(author); //Date was modified

        //.save function works both as save function and as update function


        //bookRepo.save is not required : bcz it will be auto-called by cascading
        //effect

        return "Book Added successfully";
    }
}
