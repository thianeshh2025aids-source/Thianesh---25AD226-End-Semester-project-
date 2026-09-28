package com.example.seatplan.controller;

import com.example.seatplan.entity.ExamSession;
import com.example.seatplan.service.ExamSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin
public class ExamSessionController {

    private final ExamSessionService examService;

    public ExamSessionController(
            ExamSessionService examService) {

        this.examService = examService;
    }

    @PostMapping
    public ExamSession addExam(
            @RequestBody ExamSession exam) {

        return examService.addExam(exam);
    }

    @GetMapping
    public List<ExamSession> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public ExamSession getExam(
            @PathVariable Long id) {

        return examService.getExamById(id);
    }

    @PutMapping("/{id}")
    public ExamSession updateExam(
            @PathVariable Long id,
            @RequestBody ExamSession exam) {

        return examService.updateExam(id, exam);
    }

    @DeleteMapping("/{id}")
    public String deleteExam(
            @PathVariable Long id) {

        examService.deleteExam(id);

        return "Exam deleted successfully";
    }
}