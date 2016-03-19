package com.epam.moovies.discount.calculator;

import com.epam.moovies.model.Ticket;

public interface DiscountCalculator {
     Long getDiscount(Ticket ticket);
}
