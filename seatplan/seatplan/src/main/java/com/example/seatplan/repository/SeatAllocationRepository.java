package com.example.seatplan.repository;

import com.example.seatplan.entity.SeatAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatAllocationRepository
        extends JpaRepository<SeatAllocation, Long> {

    List<SeatAllocation> findByExamSessionId(Long examId);

    Optional<SeatAllocation> findByExamSessionIdAndStudentId(
            Long examId,
            Long studentId
    );

    Optional<SeatAllocation> findByExamSessionIdAndSeatId(
            Long examId,
            Long seatId
    );

    Optional<SeatAllocation> findByStudentRegisterNumber(
            String registerNumber
    );
}