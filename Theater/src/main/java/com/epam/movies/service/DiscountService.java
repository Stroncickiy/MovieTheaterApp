package com.epam.movies.service;


import com.epam.movies.model.Ticket;


public interface DiscountService {
    Ticket applyDiscounts(Ticket ticket);

    Long getPriceOfTicket(Ticket ticket);

    Ticket evaluateAndSetTicketPrice(Ticket ticket);
}
