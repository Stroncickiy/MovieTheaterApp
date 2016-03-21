package com.epam.movies.discount.calculator.impl;

import com.epam.movies.discount.calculator.DiscountCalculator;
import com.epam.movies.model.Ticket;
import com.epam.movies.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Every10TicketDiscountCalculator implements DiscountCalculator {

	@Autowired
	private BookingService bookingService;

	@Override
	public Long getDiscount(Ticket ticket) {
		Long discount = 0L;
		Long nubmberOfTickets = bookingService.getNumberOfTicketsForUser(ticket.getCustomer());
		if (nubmberOfTickets != 0 && nubmberOfTickets % 10 == 0) {
			discount = ticket.getRealPrice() / 2;
		}
		return discount;
	}
}
