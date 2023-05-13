package com.example.student_library.Repositories;

import com.example.student_library.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
