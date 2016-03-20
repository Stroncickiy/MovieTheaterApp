package com.epam.moovies.dao;

import com.epam.moovies.model.Seat;
import com.epam.moovies.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class TicketDAO extends AbstractDAO<Ticket> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1820458173941123077L;

	@Autowired
	private SeatsDAO seatsDAO;

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private EventDAO eventDAO;

	@Override
	public Ticket add(Ticket item) {
		String query = "INSERT INTO tickets (customerId,eventId,totalPrice,realPrice,discountStrategy) VALUES (?,?,?,?,?)";
		List<Seat> bookedSeats = item.getBookedSeats();

		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, item.getCustomer().getId());
			preparedStatement.setLong(2, item.getEvent().getId());
			preparedStatement.setLong(3, item.getTotalPrice());
			preparedStatement.setLong(4, item.getRealPrice());
			preparedStatement.setString(5, item.getDiscountStrategy().name());
			return preparedStatement;
		}, holder);
		Long newItemID = holder.getKey().longValue();
		seatsDAO.assignSeatsWithTicket(bookedSeats, newItemID);
		item.setId(newItemID);
		return item;
	}

	@Override
	public boolean update(Ticket item) {
		String query = "UPDATE  tickets SET customerId=?,eventId=?,totalPrice=?,realPrice=?,discountStrategy=? WHERE id=?";
		int updated = jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, item.getCustomer().getId());
			preparedStatement.setLong(2, item.getEvent().getId());
			preparedStatement.setLong(3, item.getTotalPrice());
			preparedStatement.setLong(4, item.getRealPrice());
			preparedStatement.setString(5, item.getDiscountStrategy().name());
			preparedStatement.setLong(6, item.getId());
			return preparedStatement;
		});
		return updated > 0;
	}

	@Override
	public boolean remove(Long key) {
		String query = "DELETE FROM tickets  WHERE id=? ";
		int removed = jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, key);
			return preparedStatement;
		});
		return removed > 0;
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
		ticket.setDiscountStrategy(null); // TODO
		ticket.setRealPrice(resultSet.getLong("realPrice"));
		ticket.setTotalPrice(resultSet.getLong("totalPrice"));
		return ticket;
	}

}
