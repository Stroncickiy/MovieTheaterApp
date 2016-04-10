package com.epam.movies.dao.impl;

import com.epam.movies.dao.SeatsDAO;
import com.epam.movies.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class SeatsDAOImpl implements SeatsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Seat add(Seat item) {
        String query = "INSERT INTO seats (isVip,number,auditory) VALUES (?,?,?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBoolean(1, item.isVip());
            preparedStatement.setLong(2, item.getNumber());
            preparedStatement.setLong(3, item.getAuditoryId());
            return preparedStatement;
        });
        return item;
    }

    @Override
    public boolean update(Seat item) {
        String query = "UPDATE  seats SET isVip = ? WHERE number=? AND auditory=?   ";
        return 0 < jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, item.isVip());
            preparedStatement.setLong(2, item.getNumber());
            preparedStatement.setLong(3, item.getAuditoryId());
            return preparedStatement;
        });
    }

    @Override
    public boolean remove(Long key) {
        String query = "DELETE FROM seats  WHERE id=? ";
        return 0 < jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, key);
            return preparedStatement;
        });
    }

    @Override
    public List<Seat> getAll() {
        String query = "SELECT * FROM seats ";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            return getSeatFromRS(resultSet);
        });
    }

    @Override
    public Seat getById(Long key) {
        String query = "SELECT * FROM seats WHERE id=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getSeatFromRS(resultSet);
        }, key);
    }

    public List<Seat> getSeatsForTicket(long id) {
        String query = "SELECT * FROM seats s  JOIN  seats_tickets st ON s.number=st.seat_number AND s.auditory=st.auditory_id WHERE st.ticket_id=? ;";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            return getSeatFromRS(resultSet);
        }, id);
    }

    private Seat getSeatFromRS(ResultSet resultSet) throws SQLException {
        Seat seat = new Seat();
        seat.setNumber(resultSet.getLong("number"));
        seat.setAuditoryId(resultSet.getLong("auditory"));
        seat.setVip(resultSet.getBoolean("isVip"));
        return seat;
    }

    public List<Seat> getSeatsByNumberAndAuditorium(Long auditoriumId, Long[] seatIds) {
        String query = "SELECT * FROM seats s  WHERE auditory=? AND number IN (";
        StringBuilder queryBuilder = new StringBuilder(query);
        for (int i = 0; i < seatIds.length; i++) {
            queryBuilder.append("?");
            if (i != seatIds.length - 1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(")");
        query = queryBuilder.toString();
        return jdbcTemplate.query(query, ps -> {
            ps.setLong(1, auditoriumId);
            for (int i = 0; i < seatIds.length; i++) {
                ps.setLong(i + 2, seatIds[i]);
            }
        }, (rs, i) -> {
            return getSeatFromRS(rs);
        });
    }

    public void assignSeatsWithTicket(List<Seat> bookedSeats, Long ticketId) {
        String query = "INSERT INTO seats_tickets (seat_number,auditory_id,ticket_id) VALUES (?,?,?)";
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setLong(1, bookedSeats.get(i).getNumber());
                preparedStatement.setLong(2, bookedSeats.get(i).getAuditoryId());
                preparedStatement.setLong(3, ticketId);
            }

            @Override
            public int getBatchSize() {
                return bookedSeats.size();
            }
        });

    }

    public List<Seat> getSeatsForAuditorium(Long auditoriumId) {
        String query = "SELECT * FROM seats s  WHERE auditory=? ";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            return getSeatFromRS(resultSet);
        }, auditoriumId);
    }
}
