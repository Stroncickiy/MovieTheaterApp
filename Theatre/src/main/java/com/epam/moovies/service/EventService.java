package com.epam.moovies.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.epam.moovies.model.Auditorium;
import com.epam.moovies.model.Event;

public interface EventService {
	Event create(Event event);

	boolean remove(Long id);

	Event getByName(String name);

	List<Event> getAll();

	List<Event> getForDateRange(LocalDate from, LocalDate to);

	boolean assignAuditorium(Event event, Auditorium auditorium, LocalDateTime start, LocalDateTime end);

	Event getById(Long eventId);

}
