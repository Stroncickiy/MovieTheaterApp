package com.epam.movies.discount.calculator;

import com.epam.movies.model.Ticket;

public interface DiscountCalculator {
     Long getDiscount(Ticket ticket);
}
