package com.epam.moovies.discount.calculator.impl;

import com.epam.moovies.discount.calculator.DiscountCalculator;
import com.epam.moovies.model.Ticket;
import com.epam.moovies.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Every10TicketDiscountCalculator implements DiscountCalculator {


    @Autowired
    private BookingService bookingService;

    @Override
    public Long getDiscount(Ticket ticket) {
        Long discount = 0L;
        List<Ticket> ticketsForUser = bookingService.getTicketsForUser(ticket.getCustomer());
        if(ticketsForUser.size() % 10 == 0){
            discount = ticket.getRealPrice()/2;
        }
        return discount;
    }
}
