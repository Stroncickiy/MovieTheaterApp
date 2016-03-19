package com.epam.moovies.service;


import com.epam.moovies.model.Ticket;


public interface DiscountService {
    Ticket applyDiscounts(Ticket ticket);

    Long getPriceOfTicket(Ticket ticket);

    Ticket evaluateAndSetTicketPrice(Ticket ticket);
}
