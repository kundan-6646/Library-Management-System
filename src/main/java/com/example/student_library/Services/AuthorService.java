package com.example.student_library.Services;

import com.example.student_library.DTOs.AuthorEntryDTO;
import com.example.student_library.Models.Author;
import com.example.student_library.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(AuthorEntryDTO authorEntryDTO) {
        Author author = new Author();
        author.setName(authorEntryDTO.getName());
        author.setAge(authorEntryDTO.getAge());
        author.setRating(authorEntryDTO.getRating());

        authorRepository.save(author);
        return "Author Added Successfully!";
    }
}
