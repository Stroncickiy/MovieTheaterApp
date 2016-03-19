package com.epam.moovies.service;

import com.epam.moovies.model.Auditorium;
import com.epam.moovies.model.Seat;

import java.util.List;



public interface AuditoriumService {

    void addAuditorium(Auditorium auditorium);

    List<Auditorium> getAll();

    int getSeatsNumber(Long id);

    List<Seat> getSeats(Long id);

    Auditorium getByName(String name);

	Auditorium getById(Long auditoryId);


}
