package com.epam.movies.dao;

import com.epam.movies.model.User;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class UserDAO extends AbstractDAO<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -259449168474424436L;

	@Override
	public User add(User item) {
		String query = "INSERT INTO users (email,firstName,lastName,birthDate,password,enabled) VALUES (?,?,?,?,?,?,?)";
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, item.getEmail());
			preparedStatement.setString(2, item.getFirstName());
			preparedStatement.setString(3, item.getLastName());
			preparedStatement.setDate(4, new Date(item.getBirthDate().getTime()));
			preparedStatement.setString(5, item.getPassword());
			preparedStatement.setBoolean(6, item.isEnabled());
			return preparedStatement;
		}, holder);
		Long newEventID = holder.getKey().longValue();
		item.setId(newEventID);
		return item;
	}

	@Override
	public boolean update(User item) {
		String query = "UPDATE  users SET email=?,firstName=?,lastName=?,birthDate=?,password=?,enabled=? WHERE id=?";
		int updated = jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, item.getId());
			preparedStatement.setString(2, item.getFirstName());
			preparedStatement.setString(3, item.getLastName());
			preparedStatement.setDate(4, new Date(item.getBirthDate().getTime()));
			preparedStatement.setString(5, item.getPassword());
			preparedStatement.setBoolean(6, item.isEnabled());
			preparedStatement.setLong(7, item.getId());
			return preparedStatement;
		});
		return updated > 0;
	}

	@Override
	public boolean remove(Long key) {
		String query = "DELETE FROM users  WHERE id=? ";
		int removed = jdbcTemplate.update(connection -> {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, key);
			return preparedStatement;
		});
		return removed > 0;
	}

	@Override
	public List<User> getAll() {
		String query = "SELECT * FROM users ";
		List<User> userstList = jdbcTemplate.query(query, (resultSet, i) -> {
			return getUserFromRS(resultSet);
		});
		return userstList;
	}

	@Override
	public User getById(Long key) {
		String query = "SELECT * FROM users WHERE id=?";
		return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
			return getUserFromRS(resultSet);
		}, key);
	}

	private User getUserFromRS(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		user.setFirstName(resultSet.getString("firstName"));
		user.setId(resultSet.getLong("id"));
		user.setBirthDate(resultSet.getDate("birthDate"));
		user.setEnabled(resultSet.getBoolean("enabled"));
		user.setLastName(resultSet.getString("lastName"));
		user.setUserRoles(null); // TODO query user roles from database
		return user;
	}

}
