package com.example.seatplan.service;

import com.example.seatplan.entity.ExamSession;
import com.example.seatplan.repository.ExamSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamSessionService {

    private final ExamSessionRepository examRepository;

    public ExamSessionService(
            ExamSessionRepository examRepository) {

        this.examRepository = examRepository;
    }

    public ExamSession addExam(ExamSession exam) {
        return examRepository.save(exam);
    }

    public List<ExamSession> getAllExams() {
        return examRepository.findAll();
    }

    public ExamSession getExamById(Long id) {

        return examRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Exam session not found"
                        ));
    }

    public ExamSession updateExam(
            Long id,
            ExamSession exam) {

        ExamSession existing = getExamById(id);

        existing.setExamName(exam.getExamName());
        existing.setSubject(exam.getSubject());
        existing.setExamDate(exam.getExamDate());

        return examRepository.save(existing);
    }

    public void deleteExam(Long id) {
        examRepository.deleteById(id);
    }
}