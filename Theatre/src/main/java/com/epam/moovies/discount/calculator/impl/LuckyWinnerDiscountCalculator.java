package com.epam.moovies.discount.calculator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.moovies.discount.calculator.DiscountCalculator;
import com.epam.moovies.model.Ticket;
import com.epam.moovies.service.BookingService;

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
