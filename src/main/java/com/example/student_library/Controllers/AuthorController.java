package com.example.student_library.Controllers;

import com.example.student_library.DTOs.AuthorEntryDTO;
import com.example.student_library.Models.Author;
import com.example.student_library.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorEntryDTO author) {
        return authorService.addAuthor(author);
    }
}
