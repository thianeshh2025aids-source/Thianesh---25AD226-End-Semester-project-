package com.example.seatplan.controller;

import com.example.seatplan.entity.Hall;
import com.example.seatplan.service.HallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
@CrossOrigin
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping
    public Hall addHall(@RequestBody Hall hall) {
        return hallService.addHall(hall);
    }

    @GetMapping
    public List<Hall> getAllHalls() {
        return hallService.getAllHalls();
    }

    @GetMapping("/{id}")
    public Hall getHall(@PathVariable Long id) {
        return hallService.getHallById(id);
    }

    @PutMapping("/{id}")
    public Hall updateHall(
            @PathVariable Long id,
            @RequestBody Hall hall) {

        return hallService.updateHall(id, hall);
    }

    @DeleteMapping("/{id}")
    public String deleteHall(@PathVariable Long id) {

        hallService.deleteHall(id);

        return "Hall deleted successfully";
    }
}