package com.example.student_library.Controllers;

import com.example.student_library.DTOs.IssueBookRequestDto;
import com.example.student_library.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("issue_book")
    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) {
        try {
            return transactionService.issueBook(issueBookRequestDto);
        }catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("return_book")
    public String returnBook(@RequestBody IssueBookRequestDto issueBookRequestDto) {
        try {
            return transactionService.returnBook(issueBookRequestDto);
        }catch (Exception e) {
            return e.getMessage();
        }

    }
}
