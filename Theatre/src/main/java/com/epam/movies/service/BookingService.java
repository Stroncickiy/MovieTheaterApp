package com.epam.movies.service;

import com.epam.movies.model.Event;
import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;

import java.util.List;

public interface BookingService {
	Long getTicketPrice(Ticket ticket);

	boolean bookTicket(Ticket ticket);

	List<Ticket> getTicketsForEvent(Event event);

	List<Ticket> getTicketsForUser(User user);

	Long getNumberOfTicketsForUser(User customer);

}
