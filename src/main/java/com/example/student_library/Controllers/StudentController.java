package com.example.student_library.Controllers;

import com.example.student_library.DTOs.StudentUpdateMobRequestDto;
import com.example.student_library.Models.Student;
import com.example.student_library.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("add")
    public String addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("get_user_name")
    public String getNameByEmail(@RequestParam String email) {
        return studentService.getNameByEmail(email);
    }

    @PutMapping("update_mobile")
    public String updateMobile(@RequestBody StudentUpdateMobRequestDto studentUpdateMobRequestDto) {
        return studentService.updateMobile(studentUpdateMobRequestDto);
    }
}
