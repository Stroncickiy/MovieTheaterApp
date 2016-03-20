package com.epam.moovies.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.moovies.dao.TicketDAO;
import com.epam.moovies.model.Event;
import com.epam.moovies.model.Ticket;
import com.epam.moovies.model.User;
import com.epam.moovies.service.BookingService;
import com.epam.moovies.service.DiscountService;
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private DiscountService discountService;


    public Long getTicketPrice(Ticket ticket ) {
        return discountService.evaluateAndSetTicketPrice(ticket).getTotalPrice();
    }

    public boolean bookTicket(Ticket ticket) {
        discountService.evaluateAndSetTicketPrice(ticket);
        return ticketDAO.add(ticket)!=null;
    }

    public List<Ticket> getTicketsForEvent(Event event) {
        return ticketDAO.getTicketsForEvent(event);
    }

    @Override
    public List<Ticket> getTicketsForUser(User user) {
        return ticketDAO.getTicketsForUser(user);
    }

	@Override
	public Long getNumberOfTicketsForUser(User customer) {
		return ticketDAO.getNumberOfTicketsForUser(customer);
	}

}
