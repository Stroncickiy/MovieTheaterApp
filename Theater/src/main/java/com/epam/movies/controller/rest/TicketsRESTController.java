package com.epam.movies.controller.rest;

import com.epam.movies.dao.TicketDAO;
import com.epam.movies.model.Ticket;
import com.epam.movies.service.BookingService;
import com.epam.movies.service.UserService;
import com.epam.movies.util.TicketToPdfConverter;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("rest")
public class TicketsRESTController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketDAO ticketDAO;


    @RequestMapping(path = "ticket", method = RequestMethod.GET, produces = "application/pdf", consumes = MediaType.ALL_VALUE)
    public void allTicketsPdf(HttpServletResponse httpResponse) throws DocumentException, IOException {
        List<Ticket> all = ticketDAO.getAll();
        if (all.size() != 0) {
            byte[] bytes = TicketToPdfConverter.buildDocumentWithTickets(all);
            httpResponse.setContentType("application/pdf");
            httpResponse.getOutputStream().write(bytes);
        } else {
            httpResponse.sendError(500);
        }
    }


    @RequestMapping(path = "ticket", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> allTicketsJson() {
        List<Ticket> all = ticketDAO.getAll();
        if (all.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @RequestMapping(path = "ticket/{ticketId}", method = RequestMethod.GET, produces = "application/pdf", consumes = MediaType.ALL_VALUE)
    public void ticketPdf(HttpServletResponse httpResponse, @PathVariable Long ticketId) throws DocumentException, IOException {
        Ticket ticket = ticketDAO.getById(ticketId);
        if (ticket != null) {
            byte[] bytes = TicketToPdfConverter.buildDocumentWithTicket(ticket);
            httpResponse.setContentType("application/pdf");
            httpResponse.getOutputStream().write(bytes);
        } else {
            httpResponse.sendError(500);
        }
    }


    @RequestMapping(path = "ticket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> bookTicketOnEvent(@RequestBody Ticket ticketToCreate) {
        boolean isBooked = bookingService.bookTicket(ticketToCreate);
        if (isBooked) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(ticketToCreate, HttpStatus.CREATED);
    }

    @RequestMapping(path = "ticket/{ticketId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId) {
        Ticket ticket = ticketDAO.getById(ticketId);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @RequestMapping(path = "ticket/{ticketId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        boolean removed = ticketDAO.remove(ticketId);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
