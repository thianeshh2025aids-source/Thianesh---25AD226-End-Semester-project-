package com.example.seatplan.controller;

import com.example.seatplan.entity.Seat;
import com.example.seatplan.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/generate/{hallId}")
    public List<Seat> generateSeats(
            @PathVariable Long hallId) {

        return seatService.generateSeats(hallId);
    }

    @GetMapping("/hall/{hallId}")
    public List<Seat> getSeats(
            @PathVariable Long hallId) {

        return seatService.getSeatsByHall(hallId);
    }
}