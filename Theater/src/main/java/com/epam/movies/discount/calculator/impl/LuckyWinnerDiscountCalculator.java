package com.epam.movies.discount.calculator.impl;

import com.epam.movies.discount.calculator.DiscountCalculator;
import com.epam.movies.model.Ticket;
import com.epam.movies.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LuckyWinnerDiscountCalculator implements DiscountCalculator {

	@Autowired
	private BookingService bookingService;

	@Override
	public Long getDiscount(Ticket ticket) {
		if (ticket.isLucky()) {
			return bookingService.getTicketPrice(ticket);
		} else {
			return 0L;
		}

	}
}
