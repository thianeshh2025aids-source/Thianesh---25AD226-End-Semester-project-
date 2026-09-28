package com.example.seatplan.controller;

import com.example.seatplan.entity.Student;
import com.example.seatplan.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(
            @RequestBody Student student) {

        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(
            @PathVariable Long id) {

        return studentService.getStudentById(id);
    }

    @GetMapping("/register/{registerNumber}")
    public Student getByRegisterNumber(
            @PathVariable String registerNumber) {

        return studentService
                .getStudentByRegisterNumber(
                        registerNumber
                );
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return studentService.updateStudent(
                id, student
        );
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return "Student deleted successfully";
    }
}