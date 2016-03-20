package com.epam.moovies.service;

import com.epam.moovies.model.Event;
import com.epam.moovies.model.Ticket;
import com.epam.moovies.model.User;

import java.util.List;

public interface BookingService {
	Long getTicketPrice(Ticket ticket);

	boolean bookTicket(Ticket ticket);

	List<Ticket> getTicketsForEvent(Event event);

	List<Ticket> getTicketsForUser(User user);

	Long getNumberOfTicketsForUser(User customer);

}
