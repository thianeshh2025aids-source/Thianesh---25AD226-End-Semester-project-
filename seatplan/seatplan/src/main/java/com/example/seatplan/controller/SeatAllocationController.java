package com.example.seatplan.controller;

import com.example.seatplan.entity.SeatAllocation;
import com.example.seatplan.service.SeatAllocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
@CrossOrigin
public class SeatAllocationController {

    private final SeatAllocationService allocationService;

    public SeatAllocationController(
            SeatAllocationService allocationService) {

        this.allocationService = allocationService;
    }

    @PostMapping
    public SeatAllocation allocateSeat(
            @RequestParam Long examId,
            @RequestParam Long studentId,
            @RequestParam Long seatId) {

        return allocationService.allocateSeat(
                examId,
                studentId,
                seatId
        );
    }

    @GetMapping("/exam/{examId}")
    public List<SeatAllocation> getAllocations(
            @PathVariable Long examId) {

        return allocationService
                .getAllocations(examId);
    }

    @GetMapping("/student/{registerNumber}")
    public SeatAllocation findStudentSeat(
            @PathVariable String registerNumber) {

        return allocationService
                .findStudentSeat(registerNumber);
    }
}