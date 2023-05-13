package com.example.student_library.Repositories;

import com.example.student_library.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);

    //select * from student where country=India;   //Return of Entities
    List<Student> findByState(String state);

}

/*
    1. Existing Functions with no definition.
    2. Existing function + with defining.
    3. New Function
 */
