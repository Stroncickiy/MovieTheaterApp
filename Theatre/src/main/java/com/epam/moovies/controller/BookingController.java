package com.epam.moovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.moovies.model.Auditorium;
import com.epam.moovies.model.Event;
import com.epam.moovies.model.Seat;
import com.epam.moovies.model.Ticket;
import com.epam.moovies.service.BookingService;
import com.epam.moovies.service.EventService;
import com.epam.moovies.service.UserService;

@RestController
public class BookingController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;


    @RequestMapping(value = "/event/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> bookPlaceForEvent(@PathVariable("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) auth.getPrincipal();
        String email = principal.getUsername();
        com.epam.moovies.model.User user = userService.getUserByEmail(email.trim());
        Event event = eventService.getByName("STARWARS");
        List<Seat> seats = event.getAuditorium().getSeats();
        Ticket ticket = new Ticket();
        ticket.setCustomer(user);
        ticket.setBookedSeats(seats);
        ticket.setEvent(event);
        Auditorium auditorium = event.getAuditorium();
        ticket.setBookedSeats(auditorium.getSeats()); // booking of all seats
        ticket.setCustomer(user);
        bookingService.bookTicket(ticket);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
