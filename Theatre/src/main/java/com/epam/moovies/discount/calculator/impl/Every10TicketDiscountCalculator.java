package com.epam.moovies.discount.calculator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.moovies.discount.calculator.DiscountCalculator;
import com.epam.moovies.model.Ticket;
import com.epam.moovies.service.BookingService;

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
