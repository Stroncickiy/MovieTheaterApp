package com.epam.movies.dao;

import java.util.List;

public interface CommonDAO<O> {

    O add(O item);

    boolean update(O item);

    boolean remove(Long key);

    List<O> getAll();

    O getById(Long key);
}
