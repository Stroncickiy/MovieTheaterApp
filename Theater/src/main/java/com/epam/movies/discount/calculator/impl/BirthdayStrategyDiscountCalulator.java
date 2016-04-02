package com.epam.movies.discount.calculator.impl;

import com.epam.movies.discount.calculator.DiscountCalculator;
import com.epam.movies.model.Seat;
import com.epam.movies.model.Ticket;
import com.epam.movies.service.impl.DiscountServiceImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BirthdayStrategyDiscountCalulator implements DiscountCalculator {

    public Long getDiscount(Ticket ticket) {
        Long discount = 0L;
        if (isUserBirthdayToday(ticket.getCustomer().getBirthDate())) {
            Long priceOfTicket = getPriceOfTicket(ticket);
            discount = Double.doubleToLongBits(priceOfTicket * 0.05);
        }
        return discount;
    }


    private boolean isUserBirthdayToday(LocalDate birthDate) {
        LocalDate now = LocalDate.now();
        return (now.getDayOfMonth() == birthDate.getDayOfMonth()) && (now.getMonth() == birthDate.getMonth());
    }

    public Long getPriceOfTicket(Ticket ticket) {
        Long resultprice = 0L;
        Long basePrice = ticket.getEvent().getBasePrice();
        List<Seat> bookedSeats = ticket.getBookedSeats();
        for (Seat seat : bookedSeats) {
            if (seat.isVip()) {
                resultprice += basePrice * DiscountServiceImpl.VIP_PLACE_KOEF;
            } else {
                resultprice += basePrice;
            }
        }
        return resultprice;
    }

}
