package com.epam.movies.controller;

import com.epam.movies.model.Event;
import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;
import com.epam.movies.model.UserAccount;
import com.epam.movies.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("tickets")
public class TicketController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserAccountService userAccountService;


    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
    public String bookPlaceForEvent(@PathVariable("id") long id,
                                    @RequestParam("targetSeats") List<String> chosenSeatsStrings, Model model, Principal principal) {

        User customer = userService.getUserByEmail(principal.getName().trim());

        Event targetEvent = eventService.getById(id);

        Long[] chosenSeats = new Long[chosenSeatsStrings.size()];
        for (int i = 0; i < chosenSeatsStrings.size(); i++) {
            chosenSeats[i] = Long.parseLong(chosenSeatsStrings.get(i));
        }

        Ticket ticket = new Ticket();
        ticket.setEvent(targetEvent);
        ticket.setCustomer(customer);
        ticket.setBookedSeats(
                auditoriumService.getSeatsByNumbersAndAuditorium(targetEvent.getAuditorium().getId(), chosenSeats));
        boolean booked = bookingService.bookTicket(ticket);
        if (!booked) {
            model.addAttribute("message", "Not enough money.. Please refill you account..");
            return "message";
        }
        return "redirect:/tickets/my";
    }

    @RequestMapping("/my")
    public String openMyTicketsPage(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName().trim());
        List<Ticket> ticketsForUser = bookingService.getTicketsForUser(user);
        model.addAttribute("tickets", ticketsForUser);
        UserAccount accountOfUser = userAccountService.getForUser(user);
        model.addAttribute("balance", accountOfUser.getBalance());
        return "tickets/userTickets";
    }

    @RequestMapping(path = "/my/get", produces = {"application/pdf"})
    public ModelAndView getTicketsAsFile(Principal principal) {
        User user = userService.getUserByEmail(principal.getName().trim());
        List<Ticket> ticketsForUser = bookingService.getTicketsForUser(user);
        return new ModelAndView("ticketsPdfView", "tickets", ticketsForUser);
    }

}
