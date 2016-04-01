package com.epam.movies.dao;


import com.epam.movies.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventDAO extends CommonDAO<Event> {
    Event getByName(String name);

    List<Event> getForDateRange(LocalDate from, LocalDate to);
}
