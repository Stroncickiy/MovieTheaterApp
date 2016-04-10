package com.epam.movies.service;

import com.epam.movies.model.Auditorium;
import com.epam.movies.model.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    Event create(Event event);

    boolean remove(Long id);

    Event getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(LocalDate from, LocalDate to);

    void assignAuditorium(Event event, Auditorium auditorium, LocalDateTime start, LocalDateTime end);

    Event getById(Long eventId);

    boolean update(Event event);

}
