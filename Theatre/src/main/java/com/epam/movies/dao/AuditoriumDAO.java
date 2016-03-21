package com.epam.movies.dao;

import com.epam.movies.model.Auditorium;
import com.epam.movies.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class AuditoriumDAO extends AbstractDAO<Auditorium> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 315257731845069439L;
	@Autowired
	private SeatsDAO seatsDAO;

	@Override
	public Auditorium add(Auditorium item) {
		String query = "INSERT INTO auditorium (name) VALUES (?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, item.getName());
			return preparedStatement;
		}, holder);
		List<Seat> seats = item.getSeats();
		Long newAurditoriumId = holder.getKey().longValue();
		for (Seat seat : seats) {
			seat.setAuditoryId(newAurditoriumId);
			seatsDAO.add(seat);
		}
		item.setId(newAurditoriumId);
		return item;
	}

	@Override
	public boolean update(Auditorium item) {
		String query = "UPDATE auditorium SET name=? WHERE id=? ";
		int updated = jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, item.getName());
			preparedStatement.setLong(2, item.getId());
			return preparedStatement;
		});
		return updated > 0;
	}

	@Override
	public boolean remove(Long key) {
		String query = "DELETE FROM auditorium  WHERE id=? ";
		int removed = jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, key);
			return preparedStatement;
		});
		return removed > 0;
	}

	@Override
	public List<Auditorium> getAll() {
		String query = "SELECT * FROM auditorium ";
		List<Auditorium> auditoriumList = jdbcTemplate.query(query, (resultSet, i) -> {
			return getAuditoriumFromRS(resultSet);
		});
		return auditoriumList;
	}

	@Override
	public Auditorium getById(Long key) {
		String query = "SELECT * FROM auditorium WHERE id=?";
		return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
			return getAuditoriumFromRS(resultSet);
		}, key);
	}

	private Auditorium getAuditoriumFromRS(ResultSet resultSet) throws SQLException {
		Auditorium auditorium = new Auditorium();
		auditorium.setId(resultSet.getLong("id"));
		auditorium.setName(resultSet.getString("name"));
		auditorium.setSeats(seatsDAO.getSeatsForAuditorium(auditorium.getId()));
		return auditorium;

	}

	public Auditorium getByName(String name) {
		String query = "SELECT * FROM auditorium WHERE name=?";
		return jdbcTemplate.query(query, new ResultSetExtractor<Auditorium>() {

			@Override
			public Auditorium extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return getAuditoriumFromRS(rs);
				} else {
					return null;
				}

			}
		}, name);
	}

}
