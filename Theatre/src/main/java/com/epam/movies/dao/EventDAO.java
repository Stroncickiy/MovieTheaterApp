package com.epam.movies.dao;

import com.epam.movies.enums.Rating;
import com.epam.movies.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EventDAO extends AbstractDAO<Event> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1079896347529798587L;
	@Autowired
	private AuditoriumDAO auditoriumDAO;

	@Override
	public Event add(Event item) {
		String query = "INSERT INTO events (name,auditoriumId,start,end,basePrice,rating) VALUES (?,?,?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, item.getName());
			preparedStatement.setLong(2, item.getAuditorium().getId());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(item.getStart()));
			preparedStatement.setTimestamp(4, Timestamp.valueOf(item.getEnd()));
			preparedStatement.setLong(5, item.getBasePrice());
			preparedStatement.setString(6, item.getRating().name());
			return preparedStatement;
		}, holder);
		Long newEventID = holder.getKey().longValue();
		item.setId(newEventID);
		return item;
	}

	@Override
	public boolean update(Event item) {
		String query = "UPDATE events SET name=?,auditoriumId=?,start=?,end=?,basePrice=?,rating=?  WHERE id=?";

		int updated = jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, item.getName());
			preparedStatement.setLong(2, item.getAuditorium().getId());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(item.getStart()));
			preparedStatement.setTimestamp(4, Timestamp.valueOf(item.getEnd()));
			preparedStatement.setLong(5, item.getBasePrice());
			preparedStatement.setString(6, item.getRating().name());
			preparedStatement.setLong(7, item.getId());
			return preparedStatement;
		});
		return updated > 0;
	}

	@Override
	public boolean remove(Long key) {
		String query = "DELETE FROM events  WHERE id=? ";
		int removed = jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, key);
			return preparedStatement;
		});
		return removed > 0;
	}

	@Override
	public List<Event> getAll() {
		String query = "SELECT * FROM events ";
		List<Event> eventsList = jdbcTemplate.query(query, (resultSet, i) -> {
			return getEventFromRS(resultSet);
		});
		return eventsList;
	}

	@Override
	public Event getById(Long key) {
		String query = "SELECT * FROM events WHERE id=?";
		return jdbcTemplate.query(query, new ResultSetExtractor<Event>() {

			@Override
			public Event extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return getEventFromRS(rs);
				} else {
					return null;
				}
			}
		}, key);
	}

	private Event getEventFromRS(ResultSet resultSet) throws SQLException {
		Event event = new Event();
		event.setId(resultSet.getLong("id"));
		event.setName(resultSet.getString("name"));
		event.setAuditorium(auditoriumDAO.getById(resultSet.getLong("auditoriumId")));
		event.setBasePrice(resultSet.getLong("basePrice"));
		event.setRating(Rating.valueOf(resultSet.getString("rating")));
		event.setStart(resultSet.getTimestamp("start").toLocalDateTime());
		event.setEnd(resultSet.getTimestamp("end").toLocalDateTime());
		return event;
	}

	public List<Event> getForDateRange(LocalDate from, LocalDate to) {
		String query = "SELECT * FROM events WHERE start>? AND start<?";
		List<Event> eventsList = jdbcTemplate.query(query, (resultSet, i) -> {
			return getEventFromRS(resultSet);
		}, from, to);
		return eventsList;
	}

	public Event getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
