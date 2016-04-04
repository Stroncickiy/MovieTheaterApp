package com.epam.movies.service.impl;


import com.epam.movies.dao.TicketDAO;
import com.epam.movies.model.Event;
import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;
import com.epam.movies.model.UserAccount;
import com.epam.movies.service.BookingService;
import com.epam.movies.service.DiscountService;
import com.epam.movies.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private UserAccountService userAccountService;


    public Long getTicketPrice(Ticket ticket) {
        return discountService.evaluateAndSetTicketPrice(ticket).getTotalPrice();
    }

    @Transactional
    public boolean bookTicket(Ticket ticket) {
        Ticket evaluatedTiket = discountService.evaluateAndSetTicketPrice(ticket);
        User customer = evaluatedTiket.getCustomer();
        UserAccount account = userAccountService.getForUser(customer);
        Long balance = account.getBalance();
        Long totalPrice = ticket.getTotalPrice();
        if (balance < totalPrice) {
            return false;
        }
        account.setBalance(balance - totalPrice);
        userAccountService.update(account);
        ticketDAO.add(evaluatedTiket);
        return true;
    }

    public List<Ticket> getTicketsForEvent(Event event) {
        return ticketDAO.getTicketsForEvent(event);
    }

    @Override
    public List<Ticket> getTicketsForUser(User user) {
        return ticketDAO.getTicketsForUser(user);
    }

    @Override
    public Long getNumberOfTicketsForUser(User customer) {
        return ticketDAO.getNumberOfTicketsForUser(customer);
    }

}
