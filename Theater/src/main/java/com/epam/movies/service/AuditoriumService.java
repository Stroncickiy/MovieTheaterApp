package com.epam.movies.service;

import com.epam.movies.model.Auditorium;
import com.epam.movies.model.Seat;

import java.util.List;

public interface AuditoriumService {

	void addAuditorium(Auditorium auditorium);

	List<Auditorium> getAll();

	int getSeatsNumber(Long id);

	List<Seat> getSeats(Long id);

	Auditorium getByName(String name);

	Auditorium getById(Long auditoryId);

	List<Seat> getSeatsByNumbersAndAuditorium(Long auditoriumId, Long[] seats);

	List<Seat> getSeatsForAuditorium(Long auditoriumId);

}
