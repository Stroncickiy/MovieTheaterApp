package com.epam.movies.dao.impl;

import com.epam.movies.dao.TicketDAO;
import com.epam.movies.discount.DiscountStrategy;
import com.epam.movies.model.Event;
import com.epam.movies.model.Seat;
import com.epam.movies.model.Ticket;
import com.epam.movies.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @Autowired
    private SeatsDAOImpl seatsDAO;

    @Autowired
    private UserDAOImpl userDAO;
    @Autowired
    private EventDAOImpl eventDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ticket add(Ticket item) {
        String query = "INSERT INTO tickets (customerId,eventId,totalPrice,realPrice,discountStrategy,dicountAmount) VALUES (?,?,?,?,?,?)";
        List<Seat> bookedSeats = item.getBookedSeats();

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, item.getCustomer().getId());
            preparedStatement.setLong(2, item.getEvent().getId());
            preparedStatement.setLong(3, item.getTotalPrice());
            preparedStatement.setLong(4, item.getRealPrice());
            preparedStatement.setString(5, item.getDiscountStrategy().name());
            preparedStatement.setLong(6, item.getDiscountAmount());
            return preparedStatement;
        }, holder);
        Long newItemID = holder.getKey().longValue();
        seatsDAO.assignSeatsWithTicket(bookedSeats, newItemID);
        item.setId(newItemID);
        return item;
    }

    @Override
    public void update(Ticket item) {
        String query = "UPDATE  tickets SET customerId=?,eventId=?,totalPrice=?,realPrice=?,discountStrategy=?,dicountAmount=? WHERE id=?";
        int updated = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, item.getCustomer().getId());
            preparedStatement.setLong(2, item.getEvent().getId());
            preparedStatement.setLong(3, item.getTotalPrice());
            preparedStatement.setLong(4, item.getRealPrice());
            preparedStatement.setString(5, item.getDiscountStrategy().name());
            preparedStatement.setLong(6, item.getDiscountAmount());
            preparedStatement.setLong(7, item.getId());
            return preparedStatement;
        });
    }

    @Override
    public void remove(Long key) {
        String query = "DELETE FROM tickets  WHERE id=? ";
        int removed = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, key);
            return preparedStatement;
        });
    }

    @Override
    public List<Ticket> getAll() {
        String query = "SELECT * FROM tickets ";
        List<Ticket> ticketsList = jdbcTemplate.query(query, (resultSet, i) -> {
            return getTicketFromRS(resultSet);
        });
        return ticketsList;
    }

    @Override
    public Ticket getById(Long key) {
        String query = "SELECT * FROM tickets WHERE id=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getTicketFromRS(resultSet);
        }, key);
    }

    private Ticket getTicketFromRS(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("id"));
        ticket.setBookedSeats(seatsDAO.getSeatsForTicket(resultSet.getLong("id")));
        ticket.setCustomer(userDAO.getById(resultSet.getLong("customerId")));
        ticket.setEvent(eventDAO.getById(resultSet.getLong("eventId")));
        DiscountStrategy discountStrategy = DiscountStrategy.valueOf(resultSet.getString("discountStrategy"));
        ticket.setDiscountStrategy(discountStrategy);
        ticket.setRealPrice(resultSet.getLong("realPrice"));
        ticket.setTotalPrice(resultSet.getLong("totalPrice"));
        ticket.setDiscountAmount(resultSet.getLong("dicountAmount"));
        return ticket;
    }

    public List<Ticket> getTicketsForUser(User customer) {
        String query = "SELECT * FROM tickets WHERE customerId=? ";
        List<Ticket> ticketsList = jdbcTemplate.query(query, (resultSet, i) -> {
            return getTicketFromRS(resultSet);
        }, customer.getId());
        return ticketsList;
    }

    public List<Ticket> getTicketsForEvent(Event event) {
        String query = "SELECT * FROM tickets WHERE eventId=? ";
        List<Ticket> ticketsList = jdbcTemplate.query(query, (resultSet, i) -> {
            return getTicketFromRS(resultSet);
        }, event.getId());
        return ticketsList;
    }

    public Long getNumberOfTicketsForUser(User customer) {
        String query = "SELECT COUNT(*) FROM tickets WHERE customerId=? ";
        Long ticketsNumber = jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return resultSet.getLong(1);
        }, customer.getId());
        return ticketsNumber;

    }

}
