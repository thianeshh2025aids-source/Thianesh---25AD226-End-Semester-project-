package com.example.seatplan.repository;

import com.example.seatplan.entity.ExamSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamSessionRepository
        extends JpaRepository<ExamSession, Long> {
}