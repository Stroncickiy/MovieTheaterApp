package com.epam.movies.dao.impl;

import com.epam.movies.dao.UserAccountDAO;
import com.epam.movies.model.User;
import com.epam.movies.model.UserAccount;
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
public class UserAccountDAOImpl implements UserAccountDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public UserAccount getForUser(User user) {
        String query = "SELECT * FROM accounts WHERE user_id=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getAccountFromRS(resultSet);
        }, user.getId());
    }


    @Override
    public UserAccount add(UserAccount item) {
        String query = "INSERT INTO accounts (balance,user_id) VALUES (?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, item.getBalance());
            preparedStatement.setLong(2, item.getUserId());
            return preparedStatement;
        }, holder);
        Long newAccountId = holder.getKey().longValue();
        item.setId(newAccountId);
        return item;
    }

    @Override
    public void update(UserAccount item) {
        String query = "UPDATE accounts SET balance=? WHERE id=?";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, item.getBalance());
            preparedStatement.setLong(2, item.getId());
            return preparedStatement;
        });

    }

    @Override
    public void remove(Long key) {
        String query = "DELETE FROM accounts WHERE id=?";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, key);
            return preparedStatement;
        });

    }

    @Override
    public List<UserAccount> getAll() {
        String query = "SELECT * FROM accounts ";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            return getAccountFromRS(resultSet);
        });
    }

    private UserAccount getAccountFromRS(ResultSet resultSet) throws SQLException {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(resultSet.getLong("id"));
        userAccount.setBalance(resultSet.getLong("balance"));
        userAccount.setUserId(resultSet.getLong("user_id"));
        return userAccount;
    }


    @Override
    public UserAccount getById(Long key) {
        String query = "SELECT * FROM accounts WHERE id=?";
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            return getAccountFromRS(resultSet);
        }, key);
    }
}
