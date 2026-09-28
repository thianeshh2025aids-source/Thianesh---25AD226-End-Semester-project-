package com.example.seatplan.service;

import com.example.seatplan.entity.Hall;
import com.example.seatplan.entity.Seat;
import com.example.seatplan.repository.HallRepository;
import com.example.seatplan.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final HallRepository hallRepository;

    public SeatService(
            SeatRepository seatRepository,
            HallRepository hallRepository) {

        this.seatRepository = seatRepository;
        this.hallRepository = hallRepository;
    }

    public List<Seat> generateSeats(Long hallId) {

        Hall hall = hallRepository.findById(hallId)
                .orElseThrow(() ->
                        new RuntimeException("Hall not found"));

        List<Seat> existingSeats =
                seatRepository.findByHallIdOrderByRowNumberAscColumnNumberAsc(
                        hallId
                );

        if (!existingSeats.isEmpty()) {
            return existingSeats;
        }

        List<Seat> seats = new ArrayList<>();

        for (int row = 1; row <= hall.getRows(); row++) {

            for (int column = 1;
                 column <= hall.getColumns();
                 column++) {

                Seat seat = new Seat();

                seat.setRowNumber(row);
                seat.setColumnNumber(column);

                seat.setSeatNumber(
                        "R" + row + "C" + column
                );

                seat.setHall(hall);

                seats.add(seat);
            }
        }

        return seatRepository.saveAll(seats);
    }

    public List<Seat> getSeatsByHall(Long hallId) {

        return seatRepository
                .findByHallIdOrderByRowNumberAscColumnNumberAsc(
                        hallId
                );
    }
}