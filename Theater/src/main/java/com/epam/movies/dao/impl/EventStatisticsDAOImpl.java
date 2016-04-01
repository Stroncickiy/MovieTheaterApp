package com.epam.movies.dao.impl;

import com.epam.movies.dao.EventStatisticsDAO;
import com.epam.movies.model.EventStatistics;
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
public class EventStatisticsDAOImpl implements EventStatisticsDAO {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public EventStatistics add(EventStatistics item) {
        String query = "INSERT INTO event_queries (name,queried_by_name,queried_price,booked) VALUES (?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getQueriedByName());
            preparedStatement.setInt(3, item.getPriceQueried());
            preparedStatement.setInt(4, item.getTicketsBoocked());
            return preparedStatement;
        }, holder);
        Long newEventStatisticsID = holder.getKey().longValue();
        item.setId(newEventStatisticsID);
        return item;
    }

    @Override
    public void update(EventStatistics item) {
        String query = "UPDATE event_queries SET name=?,queried_by_name=?,queried_price=?,booked=?  WHERE id=?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(2, item.getQueriedByName());
            preparedStatement.setInt(3, item.getPriceQueried());
            preparedStatement.setInt(4, item.getTicketsBoocked());
            preparedStatement.setLong(5, item.getId());
            return preparedStatement;
        });
    }

    @Override
    public void remove(Long key) {
        String query = "DELETE FROM event_queries  WHERE id=? ";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, key);
            return preparedStatement;
        });
    }

    @Override
    public List<EventStatistics> getAll() {
        String query = "SELECT * FROM event_queries ";
        List<EventStatistics> eventsList = jdbcTemplate.query(query, (resultSet, i) -> {
            return getEventQueryStatisticsFromRS(resultSet);
        });
        return eventsList;
    }

    private EventStatistics getEventQueryStatisticsFromRS(ResultSet resultSet) throws SQLException {
        EventStatistics statistics = new EventStatistics();
        statistics.setId(resultSet.getLong("id"));
        statistics.setName(resultSet.getString("name"));
        statistics.setQueriedByName(resultSet.getInt("queried_by_name"));
        statistics.setPriceQueried(resultSet.getInt("queried_price"));
        statistics.setTicketsBoocked(resultSet.getInt("booked"));
        return statistics;
    }

    @Override
    public EventStatistics getById(Long key) {
        String query = "SELECT * FROM event_queries WHERE id=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getEventQueryStatisticsFromRS(resultSet);
        }, key);
    }

    public EventStatistics getByName(String eventName) {
        String query = "SELECT * FROM event_queries WHERE name=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getEventQueryStatisticsFromRS(resultSet);
        }, eventName);
    }

    public boolean isEventStatisticsExist(String eventName) {
        String query = "SELECT COUNT(*)  FROM event_queries WHERE name=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> resultSet.getInt(1) > 0, eventName);
    }
}
