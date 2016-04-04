package com.epam.movies.dao;

import com.epam.movies.model.User;
import com.epam.movies.model.UserAccount;

public interface UserAccountDAO extends CommonDAO<UserAccount> {


    UserAccount getForUser(User user);
}
