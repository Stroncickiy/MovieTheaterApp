package com.epam.movies.dao.impl;

import com.epam.movies.dao.UserDAO;
import com.epam.movies.enums.UserRole;
import com.epam.movies.model.User;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User add(User item) {
        String query = "INSERT INTO users (email,password,firstName,lastName,birthDate,roles,enabled) VALUES (?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getEmail());
            preparedStatement.setString(2, item.getPassword());
            preparedStatement.setString(3, item.getFirstName());
            preparedStatement.setString(4, item.getLastName());
            preparedStatement.setDate(5, Date.valueOf(item.getBirthDate()));
            preparedStatement.setString(6, StringUtils.collectionToCommaDelimitedString(item.getRoles()));
            preparedStatement.setBoolean(7, item.isEnabled());
            return preparedStatement;
        }, holder);
        Long newEventID = holder.getKey().longValue();
        item.setId(newEventID);
        return item;
    }

    @Override
    public boolean update(User item) {
        String query = "UPDATE  users SET email=?,password=?,firstName=?,lastName=?,birthDate=?,roles=?,enabled=? WHERE id=?";
        return 0 < jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getEmail());
            preparedStatement.setString(2, item.getPassword());
            preparedStatement.setString(3, item.getFirstName());
            preparedStatement.setString(4, item.getLastName());
            preparedStatement.setDate(5, Date.valueOf(item.getBirthDate()));
            preparedStatement.setString(6, StringUtils.collectionToCommaDelimitedString(item.getRoles()));
            preparedStatement.setBoolean(7, item.isEnabled());
            preparedStatement.setLong(8, item.getId());
            return preparedStatement;
        });
    }

    @Override
    public boolean remove(Long key) {
        String query = "DELETE FROM users  WHERE id=? ";
        return 0 < jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, key);
            return preparedStatement;
        });
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users ";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            return getUserFromRS(resultSet);
        });
    }

    @Override
    public User getById(Long key) {
        String query = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return getUserFromRS(rs);
            } else {
                return null;
            }
        }, key);
    }

    private User getUserFromRS(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setId(resultSet.getLong("id"));
        user.setBirthDate(resultSet.getDate("birthDate").toLocalDate());
        user.setEnabled(resultSet.getBoolean("enabled"));
        user.setLastName(resultSet.getString("lastName"));
        user.setRoles(Splitter.on(",")
                .omitEmptyStrings()
                .trimResults()
                .splitToList(resultSet.getString("roles"))
                .stream()
                .map(UserRole::valueOf)
                .collect(Collectors.toList()));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getUserFromRS(resultSet);
        }, email);
    }
}
