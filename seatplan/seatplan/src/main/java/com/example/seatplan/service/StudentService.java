package com.example.seatplan.service;

import com.example.seatplan.entity.Student;
import com.example.seatplan.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {

        if (student.getRegisterNumber() == null ||
                student.getRegisterNumber().isBlank()) {

            throw new IllegalArgumentException(
                    "Register number is required"
            );
        }

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));
    }

    public Student getStudentByRegisterNumber(
            String registerNumber) {

        return studentRepository
                .findByRegisterNumber(registerNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Student not found"
                        ));
    }

    public Student updateStudent(
            Long id,
            Student student) {

        Student existing = getStudentById(id);

        existing.setRegisterNumber(
                student.getRegisterNumber()
        );

        existing.setStudentName(
                student.getStudentName()
        );

        existing.setSection(
                student.getSection()
        );

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}