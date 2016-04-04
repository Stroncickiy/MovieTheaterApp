package com.epam.movies.service.impl;

import com.epam.movies.dao.UserAccountDAO;
import com.epam.movies.model.User;
import com.epam.movies.model.UserAccount;
import com.epam.movies.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountDAO userAccountDAO;


    @Override
    public UserAccount getForUser(User user) {
        return userAccountDAO.getForUser(user);
    }

    @Override
    public UserAccount register(UserAccount account) {
        return userAccountDAO.add(account);
    }

    @Override
    public void update(UserAccount account) {
        userAccountDAO.update(account);
    }


    @Override
    public void delete(Long id) {
        userAccountDAO.remove(id);
    }
}
