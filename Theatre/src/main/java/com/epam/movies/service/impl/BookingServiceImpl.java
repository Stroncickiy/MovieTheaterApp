package com.epam.movies.service.impl;


import com.epam.movies.dao.TicketDAO;
import com.epam.movies.model.Event;
import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;
import com.epam.movies.service.BookingService;
import com.epam.movies.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
