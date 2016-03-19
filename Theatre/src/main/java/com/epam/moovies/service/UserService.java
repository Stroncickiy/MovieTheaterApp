package com.epam.moovies.service;

import com.epam.moovies.model.Ticket;
import com.epam.moovies.model.User;

import java.util.List;

/**
 * Created by Yaroslav_Strontsitsk on 2/4/2016.
 */
public interface UserService {

	User register(User user);

    boolean remove(long id);

    User getById(long id);

    User getUserByEmail(String email);

    User getUserByName(String name);

    List<Ticket> getBookedTickets(long id);

    List<User> getAll();

    boolean isUserExist(User user);

    boolean update(User user);

}
