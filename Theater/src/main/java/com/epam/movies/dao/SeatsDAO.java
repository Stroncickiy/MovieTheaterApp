package com.epam.movies.dao;


import com.epam.movies.model.Seat;

import java.util.List;

public interface SeatsDAO extends CommonDAO<Seat> {

    List<Seat> getSeatsByNumberAndAuditorium(Long auditoriumId, Long[] seats);

    List<Seat> getSeatsForAuditorium(Long auditoriumId);
}

