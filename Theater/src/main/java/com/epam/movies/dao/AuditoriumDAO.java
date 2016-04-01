package com.epam.movies.dao;


import com.epam.movies.model.Auditorium;

public interface AuditoriumDAO extends CommonDAO<Auditorium> {
    Auditorium getByName(String name);
}
