package com.epam.movies.dao;


import com.epam.movies.model.Event;
import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;

import java.util.List;

public interface TicketDAO extends CommonDAO<Ticket> {

    List<Ticket> getTicketsForEvent(Event event);

    List<Ticket> getTicketsForUser(User user);

    Long getNumberOfTicketsForUser(User customer);
}
