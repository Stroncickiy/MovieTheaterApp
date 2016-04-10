package com.epam.movies.dao.impl;

import com.epam.movies.dao.AuditoriumDAO;
import com.epam.movies.model.Auditorium;
import com.epam.movies.model.Seat;
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
public class AuditoriumDAOImpl implements AuditoriumDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SeatsDAOImpl seatsDAO;

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
        Long newAuditoriumId = holder.getKey().longValue();
        for (Seat seat : seats) {
            seat.setAuditoryId(newAuditoriumId);
            seatsDAO.add(seat);
        }
        item.setId(newAuditoriumId);
        return item;
    }

    @Override
    public boolean update(Auditorium item) {
        String query = "UPDATE auditorium SET name=? WHERE id=? ";
        return 0 < jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setLong(2, item.getId());
            return preparedStatement;
        });

    }

    @Override
    public boolean remove(Long key) {
        String query = "DELETE FROM auditorium  WHERE id=? ";
        return 0 < jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, key);
            return preparedStatement;
        });
    }

    @Override
    public List<Auditorium> getAll() {
        String query = "SELECT * FROM auditorium ";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            return getAuditoriumFromRS(resultSet);
        });
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
        return jdbcTemplate.query(query, rs -> {
            if (rs.next()) {
                return getAuditoriumFromRS(rs);
            } else {
                return null;
            }

        }, name);
    }

}
