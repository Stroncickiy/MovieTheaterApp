package com.epam.moovies.discount.calculator.impl;

import com.epam.moovies.discount.calculator.DiscountCalculator;
import com.epam.moovies.model.Ticket;
import com.epam.moovies.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

public class LuckyWinnerDiscountCalculator implements DiscountCalculator {

    @Autowired
    private BookingService bookingService;

    @Override
    public Long getDiscount(Ticket ticket) {
        if(ticket.isLucky()){
            return bookingService.getTicketPrice(ticket);
        }else{
            return  0L;
        }

    }
}
