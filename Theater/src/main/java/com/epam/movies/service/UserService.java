package com.epam.movies.service;

import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;

import java.util.List;

public interface UserService {

	User register(User user);

    void remove(long id);

    User getById(long id);

    User getUserByEmail(String email);

    User getUserByName(String name);

    List<Ticket> getBookedTickets(long id);

    List<User> getAll();

    boolean isUserExist(User user);

    void update(User user);

}
