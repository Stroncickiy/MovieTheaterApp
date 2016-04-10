package com.epam.movies.service.impl;

import com.epam.movies.dao.EventDAO;
import com.epam.movies.model.Auditorium;
import com.epam.movies.model.Event;
import com.epam.movies.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@DependsOn(value = {"auditoriumService"})
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    public Event create(Event event) {
        return eventDAO.add(event);
    }

    public boolean remove(Long id) {
        return eventDAO.remove(id);
    }

    public Event getByName(String name) {
        return eventDAO.getByName(name);
    }

    public List<Event> getAll() {
        return eventDAO.getAll();
    }

    public List<Event> getForDateRange(LocalDate from, LocalDate to) {
        return eventDAO.getForDateRange(from, to);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, LocalDateTime start, LocalDateTime end) {
        event.setAuditorium(auditorium);
        event.setStart(start);
        event.setEnd(end);
        eventDAO.update(event);
    }

    @Override
    public Event getById(Long eventId) {
        return eventDAO.getById(eventId);

    }

    @Override
    public boolean update(Event event) {
        return eventDAO.update(event);
    }
}
