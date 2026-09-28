package com.example.seatplan.service;

import com.example.seatplan.entity.Hall;
import com.example.seatplan.repository.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public Hall addHall(Hall hall) {

        if (hall.getRows() <= 0 || hall.getColumns() <= 0) {
            throw new IllegalArgumentException(
                    "Rows and columns must be greater than zero"
            );
        }

        hall.setCapacity(
                hall.getRows() * hall.getColumns()
        );

        return hallRepository.save(hall);
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Hall getHallById(Long id) {
        return hallRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Hall not found"));
    }

    public Hall updateHall(Long id, Hall hall) {

        Hall existing = getHallById(id);

        existing.setHallName(hall.getHallName());
        existing.setRows(hall.getRows());
        existing.setColumns(hall.getColumns());

        existing.setCapacity(
                hall.getRows() * hall.getColumns()
        );

        return hallRepository.save(existing);
    }

    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }
}