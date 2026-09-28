package com.example.seatplan.service;

import com.example.seatplan.entity.*;
import com.example.seatplan.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatAllocationService {

    private final SeatAllocationRepository allocationRepository;
    private final ExamSessionRepository examRepository;
    private final StudentRepository studentRepository;
    private final SeatRepository seatRepository;

    public SeatAllocationService(
            SeatAllocationRepository allocationRepository,
            ExamSessionRepository examRepository,
            StudentRepository studentRepository,
            SeatRepository seatRepository) {

        this.allocationRepository = allocationRepository;
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
        this.seatRepository = seatRepository;
    }

    public SeatAllocation allocateSeat(
            Long examId,
            Long studentId,
            Long seatId) {

        ExamSession exam = examRepository.findById(examId)
                .orElseThrow(() ->
                        new RuntimeException("Exam not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() ->
                        new RuntimeException("Seat not found"));

        if (allocationRepository
                .findByExamSessionIdAndStudentId(
                        examId, studentId
                ).isPresent()) {

            throw new RuntimeException(
                    "Student already has a seat"
            );
        }

        if (allocationRepository
                .findByExamSessionIdAndSeatId(
                        examId, seatId
                ).isPresent()) {

            throw new RuntimeException(
                    "Seat already allocated"
            );
        }

        SeatAllocation allocation =
                new SeatAllocation();

        allocation.setExamSession(exam);
        allocation.setStudent(student);
        allocation.setSeat(seat);

        return allocationRepository.save(allocation);
    }

    public List<SeatAllocation> getAllocations(
            Long examId) {

        return allocationRepository
                .findByExamSessionId(examId);
    }

    public SeatAllocation findStudentSeat(
            String registerNumber) {

        return allocationRepository
                .findByStudentRegisterNumber(
                        registerNumber
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Seat allocation not found"
                        ));
    }
}